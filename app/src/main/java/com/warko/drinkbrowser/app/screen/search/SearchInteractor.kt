package com.warko.drinkbrowser.app.screen.search

import com.warko.drinkbrowser.app.Constants
import com.warko.drinkbrowser.app.common.ActionsBinder
import com.warko.drinkbrowser.app.navigation.SearchType
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val actionBinder: ActionsBinder,
    private val stateHandler: SearchStateHandler,
    private val factory: SearchUseCaseFactory
) {

    val state get() = stateHandler.state

    suspend fun handle(interaction: Interaction) {
        when (interaction) {
            is Interaction.OnInput -> onInput(interaction.type, interaction.input)
        }
    }

    private suspend fun onInput(type: SearchType, input: String) {
        stateHandler.updateSearchTerm(input)

        if (input.length < Constants.MIN_SEARCH_LENGTH) return
        actionBinder.bind {
            stateHandler.showSearchResults(factory.run(type, input))
        }
    }

    sealed interface Interaction {
        data class OnInput(val type: SearchType, val input: String) : Interaction
    }

}