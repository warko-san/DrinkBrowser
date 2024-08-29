package com.warko.drinkbrowser.app.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: MainInteractor
) : ViewModel() {

    val state get() = interactor.state
    val event get() = interactor.event

    fun init() {
        viewModelScope.launch { interactor.handle(MainInteractor.Interaction.Init) }
    }

    fun onCardClick(cardType: MainCardType) {
        viewModelScope.launch { interactor.handle(MainInteractor.Interaction.CardClick(cardType)) }
    }
}