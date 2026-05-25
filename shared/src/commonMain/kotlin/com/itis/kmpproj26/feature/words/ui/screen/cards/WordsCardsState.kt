package com.itis.kmpproj26.feature.words.ui.screen.cards

import com.itis.kmpproj26.feature.words.domain.model.Word
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.domain.model.WordLanguagePair

data class WordsCardsState(
    val isLoading: Boolean = false,
    val allWords: List<Word> = emptyList(),
    val words: List<Word> = emptyList(),
    val currentIndex: Int = 0,
    val selectedSourceLanguageFilter: WordLanguage = WordLanguage.ALL,
    val selectedTargetLanguageFilter: WordLanguage = WordLanguage.ALL,
    val availableSourceLanguageFilters: List<WordLanguage> = WordLanguagePair.SOURCE_LANGUAGE_FILTERS,
    val errorDialog: WordsCardsErrorDialog? = null,
) {
    val currentWord: Word?
        get() = words.getOrNull(currentIndex)

    val availableTargetLanguageFilters: List<WordLanguage>
        get() = WordLanguagePair.targetLanguageFiltersFor(selectedSourceLanguageFilter)
}

enum class WordsCardsErrorDialog {
    UNKNOWN
}
