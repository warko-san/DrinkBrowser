package com.warko.drinkbrowser.app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.warko.drinkbrowser.app.common.icon.IconProvider
import com.warko.drinkbrowser.app.common.icon.IconProviderImpl
import com.warko.drinkbrowser.app.localisation.manager.LocaleManager
import com.warko.drinkbrowser.app.localisation.manager.LocaleManagerImpl
import com.warko.drinkbrowser.app.localisation.provider.LocalisationProvider
import com.warko.drinkbrowser.app.localisation.provider.LocalisationProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreBinder {

    @Binds
    @Singleton
    abstract fun localisationProvider(localisationProviderImpl: LocalisationProviderImpl): LocalisationProvider

    @Binds
    @Singleton
    abstract fun iconProvider(iconProviderImpl: IconProviderImpl): IconProvider

    @Binds
    @Singleton
    abstract fun localeManager(localeManagerImpl: LocaleManagerImpl): LocaleManager

}

@Module
@InstallIn(SingletonComponent::class)
object CoreProvider {

    @Provides
    @Singleton
    fun sharedPrefs(context: Application): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun context(application: Application): Context {
        return application.applicationContext
    }

}