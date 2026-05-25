package com.itis.kmpproj26.feature.words.domain.model

data class WordLanguage(
    val code: String,
    val title: String,
) {
    val isAll: Boolean
        get() = code == ALL_CODE

    fun matchesSource(word: Word): Boolean {
        return isAll || word.sourceLanguage == code
    }

    fun matchesTarget(word: Word): Boolean {
        return isAll || word.targetLanguage == code
    }

    companion object {
        private const val ALL_CODE = "all"

        val ALL = WordLanguage(
            code = ALL_CODE,
            title = "All languages",
        )
    }
}
