package com.itis.kmpproj26.feature.words.ui.screen.cards.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.domain.model.WordLanguagePair
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsEvent
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsViewModel

class WordsCardsSourceLanguageFilterChangedEventHandler :
    BaseEventHandler<WordsCardsEvent.OnSourceLanguageFilterChanged, WordsCardsViewModel>() {

    override fun WordsCardsViewModel.obtain(event: WordsCardsEvent.OnSourceLanguageFilterChanged) {
        val availableTargetLanguages = WordLanguagePair.targetLanguageFiltersFor(event.language)
        val selectedTargetLanguage = viewState.selectedTargetLanguageFilter.takeIf { currentTarget ->
            currentTarget.isAll || availableTargetLanguages.any { it.code == currentTarget.code }
        } ?: WordLanguage.ALL

        viewState = viewState.copy(
            selectedSourceLanguageFilter = event.language,
            selectedTargetLanguageFilter = selectedTargetLanguage,
            words = viewState.allWords.filter { word ->
                WordLanguagePair.matches(
                    word = word,
                    sourceLanguage = event.language,
                    targetLanguage = selectedTargetLanguage,
                )
            },
            currentIndex = 0,
        )
    }
}

class WordsCardsTargetLanguageFilterChangedEventHandler :
    BaseEventHandler<WordsCardsEvent.OnTargetLanguageFilterChanged, WordsCardsViewModel>() {

    override fun WordsCardsViewModel.obtain(event: WordsCardsEvent.OnTargetLanguageFilterChanged) {
        viewState = viewState.copy(
            selectedTargetLanguageFilter = event.language,
            words = viewState.allWords.filter { word ->
                WordLanguagePair.matches(
                    word = word,
                    sourceLanguage = viewState.selectedSourceLanguageFilter,
                    targetLanguage = event.language,
                )
            },
            currentIndex = 0,
        )
    }
}
