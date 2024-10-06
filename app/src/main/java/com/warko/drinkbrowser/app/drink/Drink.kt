package com.warko.drinkbrowser.app.drink

class Drink(
    val id: String,
    val name: String,
    val category: String,
    val glass: String,
    val instructions: String,
    val imageUrl: String,
    val ingredients: List<Ingredient?>
)

class Ingredient(
    val name: String,
    val measure: String
)