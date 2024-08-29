package com.warko.drinkbrowser.app.localisation.manager

import android.content.Context

interface LocaleManager {

    fun setLanguage(language: String)

    fun getLanguage(): String

    fun updateResources(context: Context): Context

}