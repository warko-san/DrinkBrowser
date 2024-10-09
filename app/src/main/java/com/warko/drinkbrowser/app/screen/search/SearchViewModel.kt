package com.warko.drinkbrowser.app.screen.search

import androidx.lifecycle.ViewModel
import com.warko.drinkbrowser.app.common.ext.launch
import com.warko.drinkbrowser.app.navigation.SearchType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val interactor: SearchInteractor
) : ViewModel() {

    val state get() = interactor.state

    fun init(type: SearchType) {
        launch { interactor.handle(SearchInteractor.Interaction.Init(type)) }
    }

    fun onInput(input: String) {
        launch { interactor.handle(SearchInteractor.Interaction.OnInput(input)) }
    }

}