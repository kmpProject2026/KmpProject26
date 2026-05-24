package com.itis.kmpproj26.feature.words.ui.screen.list

import com.itis.kmpproj26.feature.words.domain.model.Word
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.domain.model.WordLanguagePair

data class WordsListState(
    val isLoading: Boolean = false,
    val allWords: List<Word> = emptyList(),
    val selectedSourceLanguageFilter: WordLanguage = WordLanguage.ALL,
    val selectedTargetLanguageFilter: WordLanguage = WordLanguage.ALL,
    val availableSourceLanguageFilters: List<WordLanguage> = WordLanguagePair.SOURCE_LANGUAGE_FILTERS,
    val errorDialog: WordsListErrorDialog? = null,
) {
    val words: List<Word>
        get() = allWords.filter { word ->
            WordLanguagePair.matches(
                word = word,
                sourceLanguage = selectedSourceLanguageFilter,
                targetLanguage = selectedTargetLanguageFilter,
            )
        }

    val availableTargetLanguageFilters: List<WordLanguage>
        get() = WordLanguagePair.targetLanguageFiltersFor(selectedSourceLanguageFilter)
}

enum class WordsListErrorDialog {
    UNKNOWN
}
