package com.itis.kmpproj26.feature.words.ui.screen.add.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordEvent
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordViewModel

class AddWordTranslationChangedEventHandler :
    BaseEventHandler<AddWordEvent.OnTranslationChanged, AddWordViewModel>() {

    override fun AddWordViewModel.obtain(event: AddWordEvent.OnTranslationChanged) {
        viewState = viewState.copy(
            translation = event.translation,
            translationError = null,
        )
    }
}
