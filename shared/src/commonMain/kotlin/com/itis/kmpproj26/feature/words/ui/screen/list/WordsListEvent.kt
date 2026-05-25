package com.itis.kmpproj26.feature.words.ui.screen.list

import com.itis.kmpproj26.feature.words.domain.model.WordLanguage

sealed class WordsListEvent {
    data object Init : WordsListEvent()
    data class OnSourceLanguageFilterChanged(val language: WordLanguage) : WordsListEvent()
    data class OnTargetLanguageFilterChanged(val language: WordLanguage) : WordsListEvent()
    data object OnBackClick : WordsListEvent()
    data object OnAddClick : WordsListEvent()
    data class OnDeleteClick(val wordId: Long) : WordsListEvent()
    data object HideErrorDialog : WordsListEvent()
}
