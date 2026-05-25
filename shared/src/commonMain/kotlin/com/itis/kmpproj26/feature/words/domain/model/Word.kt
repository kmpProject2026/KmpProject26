package com.itis.kmpproj26.feature.words.domain.model

data class Word(
    val id: Long,
    val spelling: String,
    val translation: String,
    val sourceLanguage: String = DEFAULT_SOURCE_LANGUAGE,
    val targetLanguage: String = DEFAULT_TARGET_LANGUAGE,
    val createdAtMillis: Long,
) {
    val languagePairCode: String
        get() = "$sourceLanguage-$targetLanguage"

    val languagePairTitle: String
        get() = WordLanguagePair.byCode(languagePairCode).title

    companion object {
        const val DEFAULT_SOURCE_LANGUAGE = "en"
        const val DEFAULT_TARGET_LANGUAGE = "ru"
    }
}
