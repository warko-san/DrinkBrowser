package com.warko.drinkbrowser.app.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination

@Serializable
sealed interface RootDestination : AppDestination {

    @Serializable
    data object Main : RootDestination

    @Serializable
    data class Search(val type: SearchType) : RootDestination

    @Serializable
    data object Categories : RootDestination

    @Serializable
    data object RandomCocktail : RootDestination

}

enum class SearchType {
    COCKTAIL_NAME, INGREDIENT_NAME
}