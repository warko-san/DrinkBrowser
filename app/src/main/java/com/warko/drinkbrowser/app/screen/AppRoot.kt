package com.warko.drinkbrowser.app.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.warko.drinkbrowser.app.navigation.RootDestination
import com.warko.drinkbrowser.app.screen.main.MainScreen

@Composable
fun AppRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RootDestination.Main,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<RootDestination.Main> {
            MainScreen(
                navigate = { destination -> navController.navigate(destination) }
            )
        }

        composable<RootDestination.Search> { }

        composable<RootDestination.Categories> { }

        composable<RootDestination.RandomCocktail> { }
    }
}