package com.itis.kmpproj26.feature.words.ui.screen.add

import com.itis.kmpproj26.feature.words.domain.model.WordLanguage

sealed class AddWordEvent {
    data object Init : AddWordEvent()
    data class OnSpellingChanged(val spelling: String) : AddWordEvent()
    data class OnTranslationChanged(val translation: String) : AddWordEvent()
    data class OnSourceLanguageChanged(val language: WordLanguage) : AddWordEvent()
    data class OnTargetLanguageChanged(val language: WordLanguage) : AddWordEvent()
    data object OnTranslateClick : AddWordEvent()
    data object OnSaveClick : AddWordEvent()
    data object OnBackClick : AddWordEvent()
    data object HideErrorDialog : AddWordEvent()
}
