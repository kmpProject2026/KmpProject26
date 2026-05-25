package com.itis.kmpproj26.feature.words.ui.screen.add.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordEvent
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordViewModel

class AddWordSpellingChangedEventHandler :
    BaseEventHandler<AddWordEvent.OnSpellingChanged, AddWordViewModel>() {

    override fun AddWordViewModel.obtain(event: AddWordEvent.OnSpellingChanged) {
        viewState = viewState.copy(
            spelling = event.spelling,
            spellingError = null,
        )
    }
}
