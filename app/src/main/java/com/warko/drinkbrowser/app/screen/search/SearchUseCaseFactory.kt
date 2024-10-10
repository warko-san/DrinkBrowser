package com.warko.drinkbrowser.app.screen.search

import com.warko.drinkbrowser.app.drink.Drink
import com.warko.drinkbrowser.app.drink.SearchDrinkByIngredientUseCase
import com.warko.drinkbrowser.app.drink.SearchDrinkByNameUseCase
import com.warko.drinkbrowser.app.navigation.SearchType
import javax.inject.Inject

class SearchUseCaseFactory @Inject constructor(
    private val searchByName: SearchDrinkByNameUseCase,
    private val searchByIngredient: SearchDrinkByIngredientUseCase
) {
    suspend fun run(type: SearchType, term: String): List<Drink> =
        when (type) {
            SearchType.COCKTAIL_NAME -> searchByName.run(term)
            SearchType.INGREDIENT_NAME -> searchByIngredient.run(term)
        }
}