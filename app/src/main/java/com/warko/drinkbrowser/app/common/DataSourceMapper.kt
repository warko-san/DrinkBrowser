package com.warko.drinkbrowser.app.common

fun interface DataSourceMapper<F, T> {

    fun map(from: F): T

    object Nothing : DataSourceMapper<Any, Unit> {
        override fun map(from: Any) = Unit
    }

}