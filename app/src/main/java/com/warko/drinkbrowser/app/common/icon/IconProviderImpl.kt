package com.warko.drinkbrowser.app.common.icon

import com.warko.drinkbrowser.R
import com.warko.drinkbrowser.app.common.compose.aspect.IconAspect
import javax.inject.Inject

class IconProviderImpl @Inject constructor() : IconProvider {

    override fun back(show: Boolean): IconAspect =
        IconAspect(R.drawable.ic_action_back, show = show)

    override fun close(show: Boolean): IconAspect =
        IconAspect(R.drawable.ic_action_close, show = show)

}