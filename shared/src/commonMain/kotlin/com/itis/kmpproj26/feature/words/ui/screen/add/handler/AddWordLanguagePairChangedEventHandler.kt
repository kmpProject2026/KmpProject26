package com.itis.kmpproj26.feature.words.ui.screen.add.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.domain.model.WordLanguagePair
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordEvent
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordViewModel

class AddWordSourceLanguageChangedEventHandler :
    BaseEventHandler<AddWordEvent.OnSourceLanguageChanged, AddWordViewModel>() {

    override fun AddWordViewModel.obtain(event: AddWordEvent.OnSourceLanguageChanged) {
        val availableTargetLanguages = WordLanguagePair.targetLanguagesFor(event.language)

        viewState = viewState.copy(
            selectedSourceLanguage = event.language,
            selectedTargetLanguage = availableTargetLanguages.firstOrNull()
                ?: WordLanguagePair.EN_RU.targetLanguageOption,
            translation = "",
            translationError = null,
        )
    }
}

class AddWordTargetLanguageChangedEventHandler :
    BaseEventHandler<AddWordEvent.OnTargetLanguageChanged, AddWordViewModel>() {

    override fun AddWordViewModel.obtain(event: AddWordEvent.OnTargetLanguageChanged) {
        viewState = viewState.copy(
            selectedTargetLanguage = event.language,
            translation = "",
            translationError = null,
        )
    }
}
