package com.itis.kmpproj26.feature.words.ui.screen.cards

import com.itis.kmpproj26.feature.words.domain.model.WordLanguage

sealed class WordsCardsEvent {
    data object Init : WordsCardsEvent()
    data class OnSourceLanguageFilterChanged(val language: WordLanguage) : WordsCardsEvent()
    data class OnTargetLanguageFilterChanged(val language: WordLanguage) : WordsCardsEvent()
    data object OnRepeatClick : WordsCardsEvent()
    data object OnRememberClick : WordsCardsEvent()
    data object OnAddClick : WordsCardsEvent()
    data object OnListClick : WordsCardsEvent()
    data object HideErrorDialog : WordsCardsEvent()
}
