package com.warko.drinkbrowser.app.screen.main

import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val reducer: MainReducer
) {

    val state get() = reducer.state

    suspend fun handle(interaction: Interaction) {
        when (interaction) {
            is Interaction.Init -> init()
            is Interaction.CardClick -> onCardClick(interaction.cardType)
        }
    }

    private suspend fun init() {
        // TODO
    }

    private suspend fun onCardClick(cardType: MainCardType) {
        // TODO
    }

    sealed interface Interaction {
        data object Init : Interaction
        data class CardClick(val cardType: MainCardType) : Interaction
    }
}