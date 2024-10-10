package com.warko.drinkbrowser.app.drink

import com.google.gson.annotations.SerializedName

class DrinkResponse(
    @SerializedName("drinks")
    val drinks: List<RemoteDrink>
)

class DrinkShortResponse(
    @SerializedName("drinks")
    val drinks: List<RemoteDrinkShort>
)

class RemoteDrink(
    @SerializedName("idDrink")
    val id: String,
    @SerializedName("strDrink")
    val name: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strDrinkThumb")
    val imageUrl: String,
    @SerializedName("strGlass")
    val glass: String,
    @SerializedName("strInstructions")
    val instructions: String,
    @SerializedName("strIngredient1")
    val ingredient1: String?,
    @SerializedName("strIngredient2")
    val ingredient2: String?,
    @SerializedName("strIngredient3")
    val ingredient3: String?,
    @SerializedName("strIngredient4")
    val ingredient4: String?,
    @SerializedName("strIngredient5")
    val ingredient5: String?,
    @SerializedName("strIngredient6")
    val ingredient6: String?,
    @SerializedName("strIngredient7")
    val ingredient7: String?,
    @SerializedName("strIngredient8")
    val ingredient8: String?,
    @SerializedName("strIngredient9")
    val ingredient9: String?,
    @SerializedName("strIngredient10")
    val ingredient10: String?,
    @SerializedName("strMeasure1")
    val measure1: String?,
    @SerializedName("strMeasure2")
    val measure2: String?,
    @SerializedName("strMeasure3")
    val measure3: String?,
    @SerializedName("strMeasure4")
    val measure4: String?,
    @SerializedName("strMeasure5")
    val measure5: String?,
    @SerializedName("strMeasure6")
    val measure6: String?,
    @SerializedName("strMeasure7")
    val measure7: String?,
    @SerializedName("strMeasure8")
    val measure8: String?,
    @SerializedName("strMeasure9")
    val measure9: String?,
    @SerializedName("strMeasure10")
    val measure10: String?
)

class RemoteDrinkShort(
    @SerializedName("idDrink")
    val id: String,
    @SerializedName("strDrink")
    val name: String,
    @SerializedName("strDrinkThumb")
    val imageUrl: String,
)