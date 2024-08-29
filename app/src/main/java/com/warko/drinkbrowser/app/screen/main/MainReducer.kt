package com.warko.drinkbrowser.app.screen.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainReducer @Inject constructor() {

    private val emptyState = State()
    private val _state = MutableStateFlow(emptyState)
    val state get() = _state.asStateFlow()

    data class State(
        val title: String = "",
        val searchByName: String = "",
        val searchByIngredient: String = "",
        val categories: String = "",
        val pickRandom: String = ""
    )
}