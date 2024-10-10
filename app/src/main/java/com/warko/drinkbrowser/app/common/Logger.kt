package com.warko.drinkbrowser.app.common

import android.util.Log
import kotlin.reflect.KClass

object Logger {

    inline fun log(
        klass: KClass<*>? = null,
        tag: String? = null,
        level: Int,
        e: Throwable? = null,
        block: () -> String
    ) {
        val logTag = createClassTag(klass, tag)
        val message = block()

        log(logTag, level, message, e)
    }

    fun log(tag: String, level: Int, message: String, e: Throwable?) {
        val modifiedTag = TAG_FILTER_PREFIX + tag
        when (level) {
            Log.VERBOSE -> Log.v(modifiedTag, message, e)
            Log.DEBUG -> Log.d(modifiedTag, message, e)
            Log.INFO -> Log.i(modifiedTag, message, e)
            Log.WARN -> Log.w(modifiedTag, message, e)
            Log.ERROR -> Log.e(modifiedTag, message, e)
        }
    }

    fun createClassTag(klass: KClass<*>?, default: String? = null): String {
        val defaultTag = default ?: UNKNOWN_LOG_TAG
        klass ?: return defaultTag
        return try {
            when {
                klass.java.isAnonymousClass -> klass.java.enclosingClass.simpleName
                else -> klass.simpleName ?: defaultTag
            }
        } catch (e: Throwable) {
            val errorTag = "${TAG_FILTER_PREFIX}CreateClassTag"
            Log.e(errorTag, "Can't create log tag from object $klass", e)
            defaultTag
        }
    }

    private const val TAG_FILTER_PREFIX = "**"
}