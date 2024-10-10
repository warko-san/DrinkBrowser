package com.warko.drinkbrowser.app.common

import com.warko.drinkbrowser.app.common.AppException.Describable
import kotlin.toString

open class AppException(val type: Type, cause: Throwable? = null) :
    Throwable("type is ${type.describe()}", cause) {

    override fun equals(other: Any?): Boolean {
        if (other !is AppException) return false
        if (other.type != type) return false
        if (other.message != message) return false
        return true
    }

    override fun hashCode() = type.hashCode()

    interface Describable {
        fun describe(): String
    }

    sealed interface Type : Describable {
        data class Descriptive(val title: String?, val message: String?) : Type {
            override fun describe() = "${this::class.simpleName.toString()} $message"
        }

        data object Unknown : Type
        data object NoConnection : Type
        data object NoResponseData : Type

        override fun describe() = this::class.simpleName.toString()
    }
}