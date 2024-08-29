package com.warko.drinkbrowser.app.localisation.provider

import androidx.annotation.StringRes

interface LocalisationProvider {

    fun getText(@StringRes res: Int): CharSequence

    fun getText(@StringRes res: Int, vararg substitutions: Any): String

}

fun LocalisationProvider.getLazyText(@StringRes res: Int) =
    lazy { getText(res) }

fun LocalisationProvider.getLazyString(@StringRes res: Int) =
    lazy { getText(res).toString() }