package com.warko.drinkbrowser.app.common.compose.surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.warko.drinkbrowser.app.common.compose.res.DrinkBrowserTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun PrimarySurface(
    binderEventActionBlock: Actions.() -> Unit = {},
    initialiseBlock: suspend CoroutineScope.() -> Unit = {},
    content: @Composable () -> Unit = {},
) = PrimarySurface(
    state = rememberPrimarySurfaceState(
        processEventActionBlock = binderEventActionBlock,
        initialiseBlock = initialiseBlock,
    ),
    content = content
)

@Composable
fun PrimarySurface(
    state: PrimarySurfaceState,
    content: @Composable () -> Unit = {},
) {
    DrinkBrowserTheme {
        content()
        ActionsEventBinding(
            lifecycleActions = state.processEventActionBlock,
            initialiseBlock = state.initialiseBlock
        )
    }
}

@Composable
private fun rememberPrimarySurfaceState(
    processEventActionBlock: Actions.() -> Unit = {},
    initialiseBlock: suspend CoroutineScope.() -> Unit = {},
): PrimarySurfaceState {

    val actionBlock = remember { processEventActionBlock }
    val initialise = remember { initialiseBlock }

    return remember(actionBlock, initialise) {
        PrimarySurfaceState(
            processEventActionBlock = actionBlock,
            initialiseBlock = initialise
        )
    }
}

@Immutable
data class PrimarySurfaceState(
    val processEventActionBlock: Actions.() -> Unit = {},
    val initialiseBlock: suspend CoroutineScope.() -> Unit = {}
)