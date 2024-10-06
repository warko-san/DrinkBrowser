@file:Suppress("unused")

package com.warko.drinkbrowser.app.common

import android.util.Log
import com.warko.drinkbrowser.BuildConfig


const val UNKNOWN_LOG_TAG = "LogTag"

val logger: Logger get() = Logger

inline fun logv(
    tag: String,
    e: Throwable? = null,
    block: () -> String
) = debug { logger.log(null, tag, Log.VERBOSE, e, block) }

inline fun <reified T : Any> T.logv(
    e: Throwable? = null,
    block: () -> String
) = debug { logger.log(T::class, null, Log.VERBOSE, e, block) }

inline fun logd(
    tag: String,
    e: Throwable? = null,
    block: () -> String
) = debug { logger.log(null, tag, Log.DEBUG, e, block) }

inline fun <reified T : Any> T.logd(
    e: Throwable? = null,
    block: () -> String
) = debug { logger.log(T::class, null, Log.DEBUG, e, block) }

inline fun logi(
    tag: String,
    e: Throwable? = null,
    block: () -> String
) = debug { logger.log(null, tag, Log.INFO, e, block) }

inline fun <reified T : Any> T.logi(
    e: Throwable? = null,
    block: () -> String
) = debug { logger.log(T::class, null, Log.INFO, e, block) }

inline fun logw(
    tag: String,
    e: Throwable? = null,
    block: () -> String
) = debug { logger.log(null, tag, Log.WARN, e, block) }

inline fun <reified T : Any> T.logw(
    e: Throwable? = null,
    block: () -> String
) = debug { logger.log(T::class, null, Log.WARN, e, block) }

inline fun loge(
    tag: String,
    e: Throwable? = null,
    block: () -> String = { "" }
) = debug { logger.log(null, tag, Log.ERROR, e, block) }

inline fun <reified T : Any> T.loge(
    e: Throwable? = null,
    block: () -> String = { "" }
) = debug { logger.log(T::class, null, Log.ERROR, e, block) }

inline fun debug(elseBlock: () -> Unit = {}, block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    } else {
        elseBlock()
    }
}