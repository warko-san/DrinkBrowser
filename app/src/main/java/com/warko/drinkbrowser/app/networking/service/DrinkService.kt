package com.warko.drinkbrowser.app.networking.service

import com.warko.drinkbrowser.app.drink.DrinkCategoriesResponse
import com.warko.drinkbrowser.app.drink.DrinkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkService {

    @GET("search.php")
    suspend fun searchByName(@Query("s") name: String): DrinkResponse

    @GET("filter.php")
    suspend fun searchByIngredient(@Query("i") ingredient: String): DrinkResponse

    @GET("list.php")
    suspend fun listCategories(@Query("c") category: String = "list"): DrinkCategoriesResponse

    @GET("filter.php")
    suspend fun listDrinksByCategory(@Query("c") category: String): DrinkResponse

    @GET("random.php")
    suspend fun showRandom(): DrinkResponse

}