package com.warko.drinkbrowser.app.drink

import com.warko.drinkbrowser.app.common.DataSourceMapper
import javax.inject.Inject

class DrinkMapper @Inject constructor() : DataSourceMapper<List<RemoteDrink>, List<Drink>> {

    override fun map(from: List<RemoteDrink>): List<Drink> =
        from.map { mapDrink(it) }

    private fun mapDrink(from: RemoteDrink): Drink =
        Drink(
            id = from.id,
            name = from.name,
            category = from.category,
            glass = from.glass,
            instructions = from.instructions,
            imageUrl = from.imageUrl,
            ingredients = mapIngredients(from)
        )

    private fun mapIngredients(from: RemoteDrink): List<Ingredient?> =
        listOf(
            fromIngredient(from.ingredient1, from.measure1),
            fromIngredient(from.ingredient2, from.measure2),
            fromIngredient(from.ingredient3, from.measure3),
            fromIngredient(from.ingredient4, from.measure4),
            fromIngredient(from.ingredient5, from.measure5),
            fromIngredient(from.ingredient6, from.measure6),
            fromIngredient(from.ingredient7, from.measure7),
            fromIngredient(from.ingredient8, from.measure8),
            fromIngredient(from.ingredient9, from.measure9),
            fromIngredient(from.ingredient10, from.measure10)
        )

    private fun fromIngredient(ingredient: String?, measure: String?): Ingredient? =
        ingredient?.let { p1 -> measure?.let { p2 -> Ingredient(p1, p2) } }

}