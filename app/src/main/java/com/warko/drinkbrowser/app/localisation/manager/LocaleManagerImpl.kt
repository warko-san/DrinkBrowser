package com.warko.drinkbrowser.app.localisation.manager

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale
import javax.inject.Inject

class LocaleManagerImpl @Inject constructor(
    private val prefs: SharedPreferences,
) : LocaleManager {

    override fun setLanguage(language: String) {
        saveLanguagePreference(language)
        updateLocale(true)
    }

    override fun getLanguage(): String {
        return prefs.getString(LANGUAGE_KEY, Locale.getDefault().language)
            ?: Locale.getDefault().language
    }

    override fun updateResources(context: Context): Context {
        val locale = updateLocale(false)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }

    private fun updateLocale(setDefault: Boolean): Locale {
        val locale = Locale(getLanguage())
        if (setDefault) {
            Locale.setDefault(locale)
        }
        return locale
    }

    private fun saveLanguagePreference(language: String) {
        prefs.edit().putString(LANGUAGE_KEY, language).apply()
    }

}

private const val LANGUAGE_KEY = "language"