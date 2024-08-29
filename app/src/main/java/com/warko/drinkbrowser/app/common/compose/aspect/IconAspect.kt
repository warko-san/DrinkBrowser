package com.warko.drinkbrowser.app.common.compose.aspect

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.warko.drinkbrowser.app.common.compose.res.Palette

@Immutable
data class IconAspect(
    @DrawableRes val icon: Int = ResourcesCompat.ID_NULL,
    val enable: Boolean = true,
    val show: Boolean = false,
    val tint: Color = Palette.Unspecified,
) {

    companion object {
        val EMPTY = IconAspect()
    }

}
