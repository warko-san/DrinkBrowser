package com.warko.drinkbrowser.app.screen.search

import com.warko.drinkbrowser.app.Constants
import com.warko.drinkbrowser.app.common.ActionsBinder
import com.warko.drinkbrowser.app.drink.SearchDrinkByNameUseCase
import com.warko.drinkbrowser.app.navigation.SearchType
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val actionBinder: ActionsBinder,
    private val stateHandler: SearchStateHandler,
    private val searchByName: SearchDrinkByNameUseCase
) {

    val state get() = stateHandler.state

    suspend fun handle(interaction: Interaction) {
        when (interaction) {
            is Interaction.Init -> init(interaction.type)
            is Interaction.OnInput -> onInput(interaction.input)
        }
    }

    private suspend fun init(type: SearchType) {

    }

    private suspend fun onInput(input: String) {
        stateHandler.updateSearchTerm(input)

        if (input.length < Constants.MIN_SEARCH_LENGTH) return

        actionBinder.bind {
            stateHandler.showSearchResults(searchByName.run(input))
        }
    }

    sealed interface Interaction {
        data class Init(val type: SearchType) : Interaction
        data class OnInput(val input: String) : Interaction
    }

}