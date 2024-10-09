package com.warko.drinkbrowser.app.screen.search

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.warko.drinkbrowser.app.common.compose.res.Dimen
import com.warko.drinkbrowser.app.common.compose.res.DrinkBrowserTheme
import com.warko.drinkbrowser.app.common.compose.res.Palette
import com.warko.drinkbrowser.app.common.compose.res.Size
import com.warko.drinkbrowser.app.common.compose.surface.PrimarySurface
import com.warko.drinkbrowser.app.common.compose.ui.SearchField
import com.warko.drinkbrowser.app.navigation.RootDestination
import com.warko.drinkbrowser.app.navigation.SearchType

@Composable
fun SearchScreen(
    type: SearchType,
    viewModel: SearchViewModel = hiltViewModel(),
    navigate: (RootDestination) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val composableState = rememberSearchState(
        term = state.searchTerm,
        results = state.searchResults.map { result ->
            SearchResultState(
                id = result.id,
                title = result.name,
                category = result.category,
                imageUrl = result.imageUrl
            )
        }
    )
    val onQueryChange: (String) -> Unit = remember { { viewModel.onInput(it) } }

    PrimarySurface(
        initialiseBlock = { viewModel.init(type) }
    ) {
        SearchContent(
            state = composableState,
            onQueryChange = onQueryChange
        )
    }
}

@Composable
private fun SearchContent(
    state: SearchContentState,
    onQueryChange: (String) -> Unit,
) {
    val listState = rememberLazyListState()
    val kbHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(kbHeight) {
        if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1) {
            listState.scrollBy(kbHeight.toFloat())
        }
    }

    Scaffold(
        topBar = {
            SearchField(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(Dimen.Spacing16),
                onTextChange = { onQueryChange(it) }
            )

        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .imePadding()
                .fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(Dimen.Spacing16),
                contentPadding = PaddingValues(vertical = Dimen.Spacing16)
            ) {
                items(key = { item -> item.id }, items = state.items) { item ->
                    SearchResultItem(
                        state = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dimen.Spacing16)
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchResultItem(
    state: SearchResultState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Palette.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimen.Elevation2)
    ) {
        Row {
            Spacer(
                modifier = Modifier.size(Dimen.Spacing16)
            )
            AsyncImage(
                model = state.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = Dimen.Spacing16)
                    .size(Size.ImagePreview)
                    .clip(shape = RoundedCornerShape(Dimen.Radius12))
            )
            Spacer(
                modifier = Modifier.size(Dimen.Spacing16)
            )
            Column {
                Spacer(
                    modifier = Modifier.size(Dimen.Spacing8)
                )
                Text(
                    text = state.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = Dimen.Spacing16)
                )
                Spacer(
                    modifier = Modifier.size(Dimen.Spacing8)
                )
                Text(
                    text = state.category,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = Dimen.Spacing16)
                )
            }
        }

    }
}

@Composable
private fun rememberSearchState(
    term: String,
    results: List<SearchResultState>
): SearchContentState =
    remember {
        SearchContentState(
            defaultTerm = term,
            defaultItems = results
        )
    }.also {
        it.term = term
        it.items = results
    }

@Preview
@Composable
private fun SearchContentPreview() {
    DrinkBrowserTheme {
        SearchContent(state = SearchContentState(
            defaultTerm = "Margarita",
            defaultItems = listOf(
                SearchResultState(
                    id = "1",
                    title = "Margarita",
                    category = "Cocktail",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                ),
                SearchResultState(
                    id = "2",
                    title = "Margarita",
                    category = "Cocktail",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                ),
                SearchResultState(
                    id = "3",
                    title = "Margarita",
                    category = "Cocktail",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                ),
                SearchResultState(
                    id = "4",
                    title = "Margarita",
                    category = "Cocktail",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                ),
                SearchResultState(
                    id = "5",
                    title = "Margarita",
                    category = "Cocktail",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                ),
                SearchResultState(
                    id = "6",
                    title = "Margarita",
                    category = "Cocktail",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                ),
                SearchResultState(
                    id = "7",
                    title = "Margarita",
                    category = "Cocktail",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                ),
                SearchResultState(
                    id = "8",
                    title = "Margarita",
                    category = "Cocktail",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                ),
                SearchResultState(
                    id = "9",
                    title = "Margarita",
                    category = "Cocktail",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                )
            )
        ), {})
    }
}


@Stable
class SearchContentState(
    defaultTerm: String,
    defaultItems: List<SearchResultState>,
) {
    var term by mutableStateOf(defaultTerm)
    var items by mutableStateOf(defaultItems)
}

@Stable
class SearchResultState(
    val id: String,
    val title: String,
    val category: String,
    val imageUrl: String
)