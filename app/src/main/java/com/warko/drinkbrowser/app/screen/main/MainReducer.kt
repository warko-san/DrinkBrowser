package com.warko.drinkbrowser.app.screen.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainReducer @Inject constructor(
    localisation: MainLocalisation
) {

    private val emptyState = State(
        title = localisation.title,
        searchByName = localisation.searchByName,
        searchByIngredient = localisation.searchByIngredient,
        categories = localisation.categories,
        pickRandom = localisation.pickRandom
    )
    private val _state = MutableStateFlow(emptyState)
    val state get() = _state.asStateFlow()

    suspend fun reduce(event: Event) {
        when (event) {
            is Event.Init -> init()
        }
    }

    private suspend fun init() {

    }

    data class State(
        val title: String = "",
        val searchByName: String = "",
        val searchByIngredient: String = "",
        val categories: String = "",
        val pickRandom: String = ""
    )

    sealed interface Event {
        data object Init : Event
    }
}