package com.warko.drinkbrowser.app.screen.search

import com.warko.drinkbrowser.app.drink.Drink
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SearchStateHandler @Inject constructor() {

    private val emptyState = State()
    private val _state = MutableStateFlow(emptyState)
    val state get() = _state.asStateFlow()

    data class State(
        val searchTerm: String = "",
        val searchResults: List<Drink> = emptyList()
    )

    suspend fun updateSearchTerm(term: String) {
        val state = _state.value
        _state.emit(state.copy(searchTerm = term))
    }

    suspend fun showSearchResults(results: List<Drink>) {
        val state = _state.value
        _state.emit(state.copy(searchResults = results))
    }

}