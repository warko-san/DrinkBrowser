package com.warko.drinkbrowser.app.networking

import com.warko.drinkbrowser.app.common.AppException
import com.warko.drinkbrowser.app.common.DataSourceMapper
import com.warko.drinkbrowser.app.common.logd
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.collections.set
import kotlin.jvm.Throws
import kotlin.let
import kotlin.reflect.KClass

class ServiceInvoker @Inject constructor(
    private val actions: Actions
) {

    suspend fun invokeService(block: suspend Actions.() -> Unit) =
        invokeService(DataSourceMapper.Nothing, block)

    suspend fun <F, T> invokeService(
        mapper: DataSourceMapper<F, T>,
        block: suspend Actions.() -> F
    ): T {
        try {
            val result = block(actions)
            return mapper.map(result)
        } catch (e: CancellationException) {
            logd { "Caught cancellation exception" }
            throw e
        } catch (e: UnknownHostException) {
            logd { "Caught UnknownHostException. Throwing AppException(NoConnection)" }
            throw AppException(AppException.Type.NoConnection)
        } catch (e: AppException) {
            logd { "Caught and propagated ${e::class.simpleName}, type ${e::class.simpleName}" }
            throw e
        } catch (e: HttpException) {
            logd { "Caught HttpException" }
            throw e
        } catch (e: Throwable) {
            actions.performSuppress<T>(e)?.let {
                logd { "Suppressed ${e::class.simpleName}, returned $it" }
                return it
            }
            actions.performException(e)?.let {
                logd { "Caught ${e::class.simpleName}, thrown $it" }
                throw it
            }
            logd(e) { "Uncaught ${e::class.simpleName}, thrown common unknown exception" }
            throw AppException(
                AppException.Type.Unknown,
                e
            )
        }
    }

    class Actions @Inject constructor() {

        private val pool: MutableMap<String, suspend (Throwable) -> Throwable> = mutableMapOf()
        private val suppressPool: MutableMap<String, suspend () -> Any> = mutableMapOf()

        fun addSuppressBlock(key: String, block: suspend () -> Any) {
            suppressPool[key] = block
        }

        @Throws(Throwable::class)
        suspend fun performException(e: Throwable) = pool[e::class.simpleName]?.invoke(e)

        suspend fun <T> performSuppress(e: Throwable): T? =
            e::class.simpleName?.let { suppressPool[it]?.invoke() as? T }

        fun <E : Throwable> on(e: KClass<E>, block: suspend (Throwable) -> Throwable) =
            e::class.simpleName?.let { pool[it] = block }

        inline fun <reified E : Throwable> suppress(noinline block: suspend () -> Any) =
            E::class.simpleName?.let { addSuppressBlock(it, block) }

        inline fun <reified E : Throwable> suppress() =
            E::class.simpleName?.let { addSuppressBlock(it) {} }
    }
}