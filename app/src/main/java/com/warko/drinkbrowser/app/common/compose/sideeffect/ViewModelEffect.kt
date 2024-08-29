package com.warko.drinkbrowser.app.common.compose.sideeffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun <T> ObserveEventsEffect(flow: SharedFlow<T>, block: suspend (T) -> Unit) {
    LaunchedEffect(key1 = Unit, block = {
        flow.collect {
            block(it)
        }
    }
    )
}