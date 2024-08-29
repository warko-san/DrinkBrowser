package com.warko.drinkbrowser.app.navigation

sealed interface AppDestination

sealed interface RootDestination : AppDestination {
    data object Main : RootDestination
}