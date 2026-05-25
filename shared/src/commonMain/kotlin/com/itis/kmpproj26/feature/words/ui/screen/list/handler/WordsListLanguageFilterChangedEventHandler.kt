package com.itis.kmpproj26.feature.words.ui.screen.list.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.domain.model.WordLanguagePair
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListEvent
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListViewModel

class WordsListSourceLanguageFilterChangedEventHandler :
    BaseEventHandler<WordsListEvent.OnSourceLanguageFilterChanged, WordsListViewModel>() {

    override fun WordsListViewModel.obtain(event: WordsListEvent.OnSourceLanguageFilterChanged) {
        val availableTargetLanguages = WordLanguagePair.targetLanguageFiltersFor(event.language)
        val selectedTargetLanguage = viewState.selectedTargetLanguageFilter.takeIf { currentTarget ->
            currentTarget.isAll || availableTargetLanguages.any { it.code == currentTarget.code }
        } ?: WordLanguage.ALL

        viewState = viewState.copy(
            selectedSourceLanguageFilter = event.language,
            selectedTargetLanguageFilter = selectedTargetLanguage,
        )
    }
}

class WordsListTargetLanguageFilterChangedEventHandler :
    BaseEventHandler<WordsListEvent.OnTargetLanguageFilterChanged, WordsListViewModel>() {

    override fun WordsListViewModel.obtain(event: WordsListEvent.OnTargetLanguageFilterChanged) {
        viewState = viewState.copy(selectedTargetLanguageFilter = event.language)
    }
}
