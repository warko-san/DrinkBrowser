package com.warko.drinkbrowser.app.networking.datasource

import com.warko.drinkbrowser.app.drink.DrinkCategory
import com.warko.drinkbrowser.app.drink.DrinkMapper
import com.warko.drinkbrowser.app.networking.ServiceInvoker
import com.warko.drinkbrowser.app.networking.service.DrinkService
import javax.inject.Inject

class DrinkRemoteDataSource @Inject constructor(
    private val drinkService: DrinkService,
    private val invoker: ServiceInvoker,
    private val mapper: DrinkMapper
) {

    suspend fun searchByName(name: String) = invoker.invokeService(
        block = { drinkService.searchByName(name).drinks },
        mapper = mapper
    )

    suspend fun searchByIngredient(ingredient: String) = invoker.invokeService(
        block = { drinkService.searchByIngredient(ingredient).drinks },
        mapper = mapper
    )

    suspend fun listCategories() = invoker.invokeService(
        block = { drinkService.listCategories().drinks },
        mapper = { categories -> categories.map { DrinkCategory(it.category) } }
    )

    suspend fun listDrinksByCategory(category: String) = invoker.invokeService(
        block = { drinkService.listDrinksByCategory(category).drinks },
        mapper = mapper
    )

    suspend fun showRandom() = invoker.invokeService(
        block = { drinkService.showRandom().drinks },
        mapper = mapper
    )

}