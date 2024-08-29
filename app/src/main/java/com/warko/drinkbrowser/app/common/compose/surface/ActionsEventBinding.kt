package com.warko.drinkbrowser.app.common.compose.surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@Composable
fun ActionsEventBinding(
    lifecycleActions: Actions.() -> Unit = {},
    initialiseBlock: suspend CoroutineScope.() -> Unit = {},
) {
    if (LocalInspectionMode.current) return

    val actions = Actions()
    lifecycleActions(actions)
    val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> actions.performOnCreate()
                Lifecycle.Event.ON_START -> actions.performOnStart()
                Lifecycle.Event.ON_RESUME -> actions.performOnResume()
                Lifecycle.Event.ON_PAUSE -> actions.performOnPause()
                Lifecycle.Event.ON_STOP -> actions.performOnStop()
                Lifecycle.Event.ON_DESTROY -> actions.performOnDestroy()
                Lifecycle.Event.ON_ANY -> actions.performOnAny(event)
            }
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        delay(1)
        initialiseBlock()
    }
}

class Actions {

    private var onCreate: () -> Unit = {}
    private var onStart: () -> Unit = {}
    private var onResume: () -> Unit = {}
    private var onPause: () -> Unit = {}
    private var onStop: () -> Unit = {}
    private var onDestroy: () -> Unit = {}
    private var onAnyEvent: (Lifecycle.Event) -> Unit = {}

    fun doOnCreate(block: () -> Unit) {
        onCreate = block
    }

    fun doOnStart(block: () -> Unit) {
        onStart = block
    }

    fun doOnResume(block: () -> Unit) {
        onResume = block
    }

    fun doOnPause(block: () -> Unit) {
        onPause = block
    }

    fun doOnStop(block: () -> Unit) {
        onStop = block
    }

    fun doOnDestroy(block: () -> Unit) {
        onDestroy = block
    }

    fun doOnAnyEvent(block: (Lifecycle.Event) -> Unit) {
        onAnyEvent = block
    }

    fun performOnCreate() = onCreate()
    fun performOnStart() = onStart()
    fun performOnResume() = onResume()
    fun performOnPause() = onPause()
    fun performOnStop() = onStop()
    fun performOnDestroy() = onDestroy()

    fun performOnAny(event: Lifecycle.Event) = onAnyEvent(event)
}