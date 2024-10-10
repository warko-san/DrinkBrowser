package com.warko.drinkbrowser.app.common.compose.ext

import androidx.compose.ui.Modifier

inline fun Modifier.thenIf(
    condition: Boolean,
    positive: Modifier.() -> Modifier,
    negative: Modifier.() -> Modifier = { this }
) = if (condition) {
    then(positive(this))
} else {
    then(negative(this))
}

inline fun Modifier.thenIfNotNull(
    property: Any?,
    positive: Modifier.() -> Modifier,
) = thenIf(
    condition = property != null,
    positive = positive
)