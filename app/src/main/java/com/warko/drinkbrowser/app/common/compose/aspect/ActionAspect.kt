package com.warko.drinkbrowser.app.common.compose.aspect

import androidx.compose.runtime.Immutable

@Immutable
data class ActionAspect(
    val caption: CharSequence = "",
    val enable: Boolean = false,
    val show: Boolean = false,
    val inProgress: Boolean = false,
    val payload: Any? = null
) {
    companion object {
        val EMPTY = ActionAspect()
    }
}