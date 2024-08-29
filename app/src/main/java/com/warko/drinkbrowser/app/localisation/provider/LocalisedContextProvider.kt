package com.warko.drinkbrowser.app.localisation.provider

import android.content.Context
import com.warko.drinkbrowser.app.localisation.manager.LocaleManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalisedContextProvider @Inject constructor(
    private val localeManager: LocaleManager,
    private val context: Context
) {
    fun getLocalisedContext() = localeManager.updateResources(context)
}