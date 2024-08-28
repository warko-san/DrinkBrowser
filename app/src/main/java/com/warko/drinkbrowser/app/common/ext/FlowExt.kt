package com.warko.drinkbrowser.app.common.ext

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun <E> eventFlow(): MutableSharedFlow<E> = MutableSharedFlow(
    extraBufferCapacity = EVENT_BUFFER_CAPACITY,
    onBufferOverflow = BufferOverflow.DROP_OLDEST
)

private const val EVENT_BUFFER_CAPACITY = 64