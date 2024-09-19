package com.warko.drinkbrowser.app.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.warko.drinkbrowser.app.common.compose.res.Dimen
import com.warko.drinkbrowser.app.common.compose.res.DrinkBrowserTheme
import com.warko.drinkbrowser.app.common.compose.res.Palette
import com.warko.drinkbrowser.app.common.compose.res.Size
import com.warko.drinkbrowser.app.common.compose.ui.SearchField
import com.warko.drinkbrowser.app.navigation.RootDestination
import com.warko.drinkbrowser.app.navigation.SearchType

@Composable
fun SearchScreen(
    type: SearchType,
    navigate: (RootDestination) -> Unit
) {
    SearchContent(state = SearchContentState(
        items = listOf(
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

@Composable
private fun SearchContent(
    state: SearchContentState,
    onQueryChange: (String) -> Unit,
) {
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
                .fillMaxSize()
        ) {
            LazyColumn(
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

@Preview
@Composable
private fun SearchContentPreview() {
    DrinkBrowserTheme {
        SearchContent(state = SearchContentState(
            items = listOf(
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
    val items: List<SearchResultState>
)

@Stable
class SearchResultState(
    val id: String,
    val title: String,
    val category: String,
    val imageUrl: String
)