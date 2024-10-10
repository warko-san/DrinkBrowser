package com.warko.drinkbrowser.app.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.warko.drinkbrowser.app.navigation.RootDestination
import com.warko.drinkbrowser.app.screen.details.DrinkDetailsScreen
import com.warko.drinkbrowser.app.screen.main.MainScreen
import com.warko.drinkbrowser.app.screen.search.SearchScreen

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

        composable<RootDestination.Search> {
            SearchScreen(
                type = it.toRoute<RootDestination.Search>().type,
                navigate = { destination -> navController.navigate(destination) })
        }

        composable<RootDestination.Categories> { }

        composable<RootDestination.RandomCocktail> { }

        composable<RootDestination.DrinkDetails> {
            DrinkDetailsScreen(
                id = it.toRoute<RootDestination.DrinkDetails>().id,
                navigate = { route -> navController.navigate(route) }
            )
        }
    }
}