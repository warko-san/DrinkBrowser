package com.warko.drinkbrowser.app.screen.main

import com.warko.drinkbrowser.R
import com.warko.drinkbrowser.app.localisation.provider.LocalisationProvider
import com.warko.drinkbrowser.app.localisation.provider.getLazyString
import javax.inject.Inject

class MainLocalisation @Inject constructor(
    provider: LocalisationProvider
) {
    val title by provider.getLazyString(R.string.title_main)
    val searchByName by provider.getLazyString(R.string.title_search_by_name)
    val searchByIngredient by provider.getLazyString(R.string.title_search_by_ingredient)
    val categories by provider.getLazyString(R.string.title_categories)
    val pickRandom by provider.getLazyString(R.string.title_pick_random)
}