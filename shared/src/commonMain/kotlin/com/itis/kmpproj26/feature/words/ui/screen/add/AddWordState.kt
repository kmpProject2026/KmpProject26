package com.itis.kmpproj26.feature.words.ui.screen.add

import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.domain.model.WordLanguagePair

data class AddWordState(
    val spelling: String = "",
    val translation: String = "",
    val selectedSourceLanguage: WordLanguage = WordLanguagePair.EN_RU.sourceLanguageOption,
    val selectedTargetLanguage: WordLanguage = WordLanguagePair.EN_RU.targetLanguageOption,
    val availableSourceLanguages: List<WordLanguage> = WordLanguagePair.SOURCE_LANGUAGES,
    val isTranslating: Boolean = false,
    val isSaving: Boolean = false,
    val spellingError: AddWordInputError? = null,
    val translationError: AddWordInputError? = null,
    val errorDialog: AddWordErrorDialog? = null,
) {
    val selectedLanguagePair: WordLanguagePair
        get() = WordLanguagePair.byLanguages(
            sourceLanguage = selectedSourceLanguage.code,
            targetLanguage = selectedTargetLanguage.code,
        ) ?: WordLanguagePair.EN_RU

    val availableTargetLanguages: List<WordLanguage>
        get() = WordLanguagePair.targetLanguagesFor(selectedSourceLanguage)
}

enum class AddWordInputError {
    EMPTY
}

enum class AddWordErrorDialog {
    NETWORK,
    UNKNOWN
}
