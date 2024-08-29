package com.warko.drinkbrowser.app.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.warko.drinkbrowser.app.common.compose.res.Dimen
import com.warko.drinkbrowser.app.common.compose.res.DrinkBrowserTheme
import com.warko.drinkbrowser.app.common.compose.res.Size
import com.warko.drinkbrowser.app.common.compose.sideeffect.ObserveEventsEffect
import com.warko.drinkbrowser.app.common.compose.surface.PrimarySurface
import com.warko.drinkbrowser.app.navigation.AppDestination
import com.warko.drinkbrowser.app.navigation.RootDestination

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

        composable<RootDestination.Search> {  }

        composable<RootDestination.Categories> {  }

        composable<RootDestination.RandomCocktail> {  }
    }
}

@Composable
private fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigate: (AppDestination) -> Unit = { }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val composableState = rememberMainContentState(
        title = state.title,
        searchByNameState = rememberMainCardState(
            title = state.searchByName,
            type = MainCardType.SEARCH_BY_NAME
        ),
        searchByIngredientState = rememberMainCardState(
            title = state.searchByIngredient,
            type = MainCardType.SEARCH_BY_INGREDIENT
        ),
        categoriesState = rememberMainCardState(
            title = state.categories,
            type = MainCardType.CATEGORIES
        ),
        pickRandomState = rememberMainCardState(
            title = state.pickRandom,
            type = MainCardType.PICK_RANDOM
        )
    )
    val onCardClick: (MainCardType) -> Unit = remember { { viewModel.onCardClick(it) } }

    ObserveEventsEffect(flow = viewModel.event) {
        when (it) {
            is MainInteractor.Effect.Navigate -> navigate(it.destination)
        }
    }
    PrimarySurface(
        initialiseBlock = { viewModel.init() }
    ) {
        MainContent(state = composableState, onCardClick = onCardClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContent(
    state: MainContentState,
    onCardClick: (MainCardType) -> Unit = {}
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = state.title) }) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            DoubleCardRow(
                first = state.searchByNameState,
                second = state.searchByIngredientState,
                modifier = Modifier.fillMaxWidth(),
                onCardClick = onCardClick
            )
            Spacer(
                modifier = Modifier.size(Dimen.Spacing16)
            )
            DoubleCardRow(
                first = state.categoriesState,
                second = state.pickRandomState,
                modifier = Modifier.fillMaxWidth(),
                onCardClick = onCardClick
            )
        }
    }
}

@Composable
private fun DoubleCardRow(
    first: MainCardState,
    second: MainCardState,
    modifier: Modifier,
    onCardClick: (MainCardType) -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.size(Size.MainCardSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onCardClick(first.type) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = first.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimen.Spacing16),
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(
            modifier = Modifier.size(Dimen.Spacing16)
        )
        Card(
            modifier = Modifier.size(Size.MainCardSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onCardClick(second.type) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = second.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimen.Spacing16),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun rememberMainContentState(
    title: String,
    searchByNameState: MainCardState,
    searchByIngredientState: MainCardState,
    categoriesState: MainCardState,
    pickRandomState: MainCardState
): MainContentState = remember {
    MainContentState(
        title = title,
        searchByNameState = searchByNameState,
        searchByIngredientState = searchByIngredientState,
        categoriesState = categoriesState,
        pickRandomState = pickRandomState
    )
}

@Composable
private fun rememberMainCardState(
    title: String,
    type: MainCardType
): MainCardState = remember {
    MainCardState(
        title = title,
        type = type
    )
}

@Preview
@Composable
private fun AppRootPreview() {
    DrinkBrowserTheme {
        MainContent(
            state = MainContentState(
                title = "Drink Browser",
                searchByNameState = MainCardState(
                    title = "Search by name",
                    type = MainCardType.SEARCH_BY_NAME
                ),
                searchByIngredientState = MainCardState(
                    title = "Search by ingredient",
                    type = MainCardType.SEARCH_BY_INGREDIENT
                ),
                categoriesState = MainCardState(
                    title = "Categories",
                    type = MainCardType.CATEGORIES
                ),
                pickRandomState = MainCardState(
                    title = "Pick a random cocktail",
                    type = MainCardType.PICK_RANDOM
                )
            )
        )
    }
}

@Stable
class MainContentState(
    val title: String,
    val searchByNameState: MainCardState,
    val searchByIngredientState: MainCardState,
    val categoriesState: MainCardState,
    val pickRandomState: MainCardState
)

@Stable
class MainCardState(
    val title: String,
    val type: MainCardType
)

enum class MainCardType {
    SEARCH_BY_NAME,
    SEARCH_BY_INGREDIENT,
    CATEGORIES,
    PICK_RANDOM
}