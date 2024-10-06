package com.warko.drinkbrowser.app.di

import com.warko.drinkbrowser.app.networking.service.DrinkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import kotlin.jvm.java

@Module
@InstallIn(ViewModelComponent::class)
object NetworkServiceModule {

    @Provides
    fun provideDrinkService(retrofit: Retrofit): DrinkService =
        retrofit.create(DrinkService::class.java)

}