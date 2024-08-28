package com.warko.drinkbrowser.app.common.compose

import androidx.compose.runtime.compositionLocalOf
import androidx.core.app.ComponentActivity

val LocalActivity = compositionLocalOf<ComponentActivity> { error("LocalScreen Screen not present") }