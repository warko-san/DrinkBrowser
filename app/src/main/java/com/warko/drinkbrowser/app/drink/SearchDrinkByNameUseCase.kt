package com.warko.drinkbrowser.app.drink

import com.warko.drinkbrowser.app.networking.datasource.DrinkRemoteDataSource
import javax.inject.Inject

class SearchDrinkByNameUseCase @Inject constructor(
    private val remoteSource: DrinkRemoteDataSource
) {

    suspend fun run(name: String) = remoteSource.searchByName(name)

}