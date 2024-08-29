package com.warko.drinkbrowser.app.localisation.provider

import androidx.annotation.StringRes
import javax.inject.Inject

class LocalisationProviderImpl @Inject constructor(
    private val localisedContextProvider: LocalisedContextProvider
) : LocalisationProvider {

    private val context get() = localisedContextProvider.getLocalisedContext()

    override fun getText(@StringRes res: Int): CharSequence = context.getString(res)

    override fun getText(res: Int, vararg substitutions: Any): String =
        String.format(context.getString(res), *substitutions)
}