package com.warko.drinkbrowser.app.screen.main

import com.warko.drinkbrowser.app.common.ext.eventFlow
import com.warko.drinkbrowser.app.navigation.AppDestination
import com.warko.drinkbrowser.app.navigation.RootDestination
import com.warko.drinkbrowser.app.navigation.SearchType
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val stateHandler: MainStateHandler
) {

    private val _event = eventFlow<Effect>()
    val event get() = _event.asSharedFlow()
    val state get() = stateHandler.state

    suspend fun handle(interaction: Interaction) {
        when (interaction) {
            is Interaction.Init -> init()
            is Interaction.CardClick -> onCardClick(interaction.cardType)
        }
    }

    private suspend fun init() {

    }

    private suspend fun onCardClick(cardType: MainCardType) {
        when (cardType) {
            MainCardType.SEARCH_BY_NAME -> _event.emit(
                Effect.Navigate(
                    RootDestination.Search(
                        SearchType.COCKTAIL_NAME
                    )
                )
            )

            MainCardType.SEARCH_BY_INGREDIENT -> _event.emit(
                Effect.Navigate(
                    RootDestination.Search(
                        SearchType.INGREDIENT_NAME
                    )
                )
            )

            MainCardType.CATEGORIES -> _event.emit(Effect.Navigate(RootDestination.Categories))
            MainCardType.PICK_RANDOM -> _event.emit(Effect.Navigate(RootDestination.RandomCocktail))
        }
    }

    sealed interface Interaction {
        data object Init : Interaction
        data class CardClick(val cardType: MainCardType) : Interaction
    }

    sealed interface Effect {
        data class Navigate(val destination: AppDestination) : Effect
    }
}