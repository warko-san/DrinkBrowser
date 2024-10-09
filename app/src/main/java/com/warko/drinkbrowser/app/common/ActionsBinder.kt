package com.warko.drinkbrowser.app.common

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.warko.drinkbrowser.app.common.ext.eventFlow
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import kotlin.collections.set
import kotlin.jvm.Throws
import kotlin.let

class ActionsBinder @Inject constructor(
    private val emitter: EventEmitter
) {

    val unsafeEmit get() = emitter
    val event get() = emitter.event

    suspend fun bind(
        retryPolicy: RetryPolicy = RetryPolicy.ManualSnackbar,
        progressPolicy: ProgressPolicy = ProgressPolicy.None,
        skipCancellation: Boolean = false,
        block: suspend Actions.() -> Unit
    ) {
        val actions = Actions(emitter)

        actions.invokeCatching(
            skipCancellation,
            progressPolicy,
            retryPolicy,
        ) {
            if (progressPolicy is ProgressPolicy.ShowAndHide) {
                emitter.showProgress()
            }
            block(actions)
            if (progressPolicy is ProgressPolicy.ShowAndHide) {
                emitter.hideProgress()
            }
        }
    }

    private suspend fun Actions.invokeCatching(
        skipCancellation: Boolean,
        progressPolicy: ProgressPolicy,
        retryPolicy: RetryPolicy,
        safeExceptionPerform: Boolean = true,
        block: suspend () -> Unit
    ) {
        try {
            block()
        } catch (e: Throwable) {
            if (skipCancellation && e is CancellationException) return
            val payload = createPayload()

            if (safeExceptionPerform) {
                invokeCatching(
                    skipCancellation,
                    progressPolicy,
                    retryPolicy,
                    safeExceptionPerform = false
                ) {
                    performException(e, retryPolicy, payload)
                }
            } else {
                performException(e, retryPolicy, payload)
            }

            if (progressPolicy == ProgressPolicy.None) emit.hideProgress()
        } finally {
            if (safeExceptionPerform) {
                progressEnd()
            }
        }
    }

    private suspend fun Actions.performException(
        e: Throwable,
        retryPolicy: RetryPolicy,
        payload: Any?
    ) {
        if (!suppressAllExceptions(e)) {
            if (e is AppException) {
                onAppException(this, retryPolicy, e, payload)
            } else {
                onUnknownException(this, retryPolicy, e, payload)
            }
        }
        performAfterException()
    }

    private suspend fun onAppException(
        actions: Actions,
        retryPolicy: RetryPolicy,
        e: AppException,
        payload: Any? = null
    ) {
        logd { "Caught $e" }

        val handled = actions.performStresslessExceptionHandling(e)
        if (!handled) {
            onUnknownException(actions, retryPolicy, e, payload)
        }
    }

    private suspend fun onCustomExceptionHandling(
        retryPolicy: RetryPolicy,
        e: AppException,
        payload: Any? = null
    ): Boolean {
        when (e.type) {
            AppException.Type.NoConnection -> {
                // TODO send messages
//                emitter.custom(
//                    ActionsBinderEventFactory.noConnectionEvent(
//                        retryPolicy,
//                        payload
//                    )
//                )
            }

            is AppException.Type.Descriptive -> {
                // TODO send messages
//                emitter.custom(
//                    ActionsBinderEventFactory.descriptiveErrorEvent(
//                        retryPolicy,
//                        AppException.Type.Descriptive.message,
//                        payload
//                    )
//                )
            }

            else -> return false
        }
        return true
    }

    private suspend fun onUnknownException(
        actions: Actions,
        retryPolicy: RetryPolicy,
        e: Throwable,
        payload: Any? = null
    ) {
        logd(e) { "Caught Unknown ${e::class.simpleName}" }
        var handled = actions.performExceptionHandling(e)
        if (!handled) handled = actions.suppress(e)
        if (!handled) handled = actions.performAllExceptionHandling(e)
        if (!handled && e is AppException) handled =
            onCustomExceptionHandling(retryPolicy, e, payload)
        // TODO send messages
//        if (!handled) emitter.custom(
//
//        )
    }

    class Actions(
        val emit: EventEmitter
    ) {

        private var suppressSettings: (suspend (Throwable) -> Unit)? = null
        private var suppressAllBlock: (suspend (Throwable) -> Unit)? = null
        private var universalBlock: (suspend (Throwable) -> Unit)? = null
        private var afterBlock: (suspend () -> Unit)? = null
        private var trackProgressBlock: suspend (Boolean) -> Unit = { }

        private var payloadFactory: () -> Any? = { null }
        private val exceptionPool:
                MutableMap<String, suspend (Throwable) -> Boolean> = mutableMapOf()

        @Throws
        suspend fun suppressAllExceptions(e: Throwable): Boolean =
            suppressAllBlock?.invoke(e) == null

        @Throws
        internal suspend fun suppress(throwable: Throwable) =
            suppressSettings?.let { block ->
                block(throwable)
                true
            } ?: false

        @Throws(Throwable::class)
        suspend fun performStresslessExceptionHandling(e: AppException): Boolean =
            exceptionPool[e::class.simpleName]?.invoke(e) == true

        @Throws(Throwable::class)
        suspend fun performExceptionHandling(e: Throwable): Boolean =
            exceptionPool[e::class.simpleName]?.invoke(e) == true

        @Throws(Throwable::class)
        suspend fun performAllExceptionHandling(
            e: Throwable
        ): Boolean = universalBlock?.invoke(e) == null

        @Throws(Throwable::class)
        suspend fun performAfterException(): Boolean = afterBlock?.invoke() == null

        suspend fun progressStart() = trackProgressBlock(true)
        suspend fun progressEnd() = trackProgressBlock(false)

        @Throws
        fun onSuppress(
            block: suspend (Throwable) -> Unit
        ) {
            suppressSettings = block
        }

        fun on(vararg types: AppException.Type, block: suspend () -> Unit) {
            for (type in types) {
                type::class.simpleName?.let {
                    exceptionPool[it] = { block(); true }
                }
            }
        }

        fun after(block: suspend () -> Unit) {
            afterBlock = block
        }

        fun onCreatePayload(block: () -> Any?) {
            payloadFactory = block
        }

        fun createPayload() = payloadFactory()

        suspend fun trackProgress(block: suspend (inProgress: Boolean) -> Unit) {
            trackProgressBlock = block
            progressStart()
        }
    }

    class EventEmitter @Inject constructor() {

        private val _event = eventFlow<Event>()
        val event: SharedFlow<Event> = _event.asSharedFlow()

        suspend fun toast(toast: Event.Toast) =
            _event.emit(toast)

        suspend fun silentError() =
            _event.emit(Event.SilentError)

        suspend fun showProgress() =
            _event.emit(Event.ShowProgress)

        suspend fun hideProgress() =
            _event.emit(Event.HideProgress)

        suspend fun showSnackBar(alert: Event.SnackBar) =
            _event.emit(alert)

        suspend fun hideSnackBar() =
            _event.emit(Event.HideSnackBar)

        suspend fun toClipboard(text: String) = _event.emit(Event.Clipboard(text))
    }

    sealed interface Event {

        data class SnackBar(
            val text: String,
            @ColorRes
            val backgroundColor: Int,
            @DrawableRes
            val icon: Int? = null,
            @ColorRes
            val textColor: Int? = null,
            val actionText: String? = null,
            val appearance: SnackBarAppearance = SnackBarAppearance.Manual(),
            val hidePrevious: Boolean = true
        ) : Event

        data object HideSnackBar : Event

        data class Toast(val message: CharSequence) : Event

        data object SilentError : Event

        data object ShowProgress : Event

        data object HideProgress : Event

        data class Clipboard(val string: String) : Event
    }

    sealed interface RetryPolicy {

        data class BlockingSnackbar(
            val showOverlay: Boolean = true,
            val showUnderToolbar: Boolean = true
        ) : RetryPolicy

        data object ManualSnackbar : RetryPolicy

        data object Silent : RetryPolicy
    }

    sealed interface SnackBarAppearance {
        data class Blocking(val showOverlay: Boolean = true, val showUnderToolbar: Boolean = true) :
            SnackBarAppearance

        data class Manual(val autoDismissDuration: Long = 0L) : SnackBarAppearance
    }

    sealed interface ProgressPolicy {
        data object None : ProgressPolicy

        data object ShowAndHide : ProgressPolicy
    }
}