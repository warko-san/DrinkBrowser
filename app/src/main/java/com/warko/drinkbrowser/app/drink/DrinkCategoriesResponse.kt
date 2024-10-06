package com.warko.drinkbrowser.app.drink

import com.google.gson.annotations.SerializedName

class DrinkCategoriesResponse(
    @SerializedName("drinks")
    val drinks: List<RemoteDrinkCategory>
)

class RemoteDrinkCategory(
    @SerializedName("strCategory")
    val category: String
)