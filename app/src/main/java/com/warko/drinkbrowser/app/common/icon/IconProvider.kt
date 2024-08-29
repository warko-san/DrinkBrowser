package com.warko.drinkbrowser.app.common.icon

import com.warko.drinkbrowser.app.common.compose.aspect.IconAspect

interface IconProvider {

    fun back(show: Boolean = true): IconAspect

    fun close(show: Boolean = true): IconAspect

}