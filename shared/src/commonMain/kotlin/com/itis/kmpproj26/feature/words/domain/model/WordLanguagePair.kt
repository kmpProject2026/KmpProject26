package com.itis.kmpproj26.feature.words.domain.model

data class WordLanguagePair(
    val code: String,
    val sourceLanguage: String,
    val targetLanguage: String,
    val title: String,
) {
    val sourceLanguageOption: WordLanguage
        get() = languageByCode(sourceLanguage)

    val targetLanguageOption: WordLanguage
        get() = languageByCode(targetLanguage)

    val isAll: Boolean
        get() = code == ALL_CODE

    fun matches(word: Word): Boolean {
        return isAll ||
            word.sourceLanguage == sourceLanguage &&
            word.targetLanguage == targetLanguage
    }

    companion object {
        private const val ALL_CODE = "all"

        val ALL = WordLanguagePair(
            code = ALL_CODE,
            sourceLanguage = "",
            targetLanguage = "",
            title = "All languages",
        )

        private val LANGUAGE_NAMES = mapOf(
            "be" to "Belarusian",
            "bg" to "Bulgarian",
            "cs" to "Czech",
            "da" to "Danish",
            "de" to "German",
            "el" to "Greek",
            "emj" to "Emoji",
            "en" to "English",
            "es" to "Spanish",
            "et" to "Estonian",
            "fi" to "Finnish",
            "fr" to "French",
            "hu" to "Hungarian",
            "it" to "Italian",
            "lt" to "Lithuanian",
            "lv" to "Latvian",
            "mhr" to "Meadow Mari",
            "mrj" to "Hill Mari",
            "nl" to "Dutch",
            "no" to "Norwegian",
            "pl" to "Polish",
            "pt" to "Portuguese",
            "pt-BR" to "Portuguese (Brazil)",
            "ru" to "Russian",
            "sk" to "Slovak",
            "sv" to "Swedish",
            "tr" to "Turkish",
            "tt" to "Tatar",
            "uk" to "Ukrainian",
            "zh" to "Chinese",
        )

        private val LANGUAGE_CODES = LANGUAGE_NAMES.keys.sortedByDescending { it.length }

        private val PREFERRED_CODES = listOf(
            "en-ru",
            "ru-en",
            "en-en",
            "ru-ru",
        )

        private val SUPPORTED_CODES = listOf(
            "be-be",
            "be-ru",
            "bg-ru",
            "cs-cs",
            "cs-en",
            "cs-ru",
            "da-en",
            "da-ru",
            "de-de",
            "de-en",
            "de-ru",
            "de-tr",
            "el-en",
            "el-ru",
            "emj-en",
            "emj-es",
            "emj-fr",
            "emj-it",
            "emj-pt",
            "emj-ru",
            "emj-tr",
            "en-cs",
            "en-da",
            "en-de",
            "en-el",
            "en-en",
            "en-es",
            "en-et",
            "en-fi",
            "en-fr",
            "en-it",
            "en-lt",
            "en-lv",
            "en-nl",
            "en-no",
            "en-pt",
            "en-pt-BR",
            "en-ru",
            "en-sk",
            "en-sv",
            "en-tr",
            "en-uk",
            "es-en",
            "es-es",
            "es-ru",
            "et-en",
            "et-ru",
            "fi-en",
            "fi-fi",
            "fi-ru",
            "fr-en",
            "fr-fr",
            "fr-ru",
            "hu-hu",
            "hu-ru",
            "it-en",
            "it-it",
            "it-ru",
            "lt-en",
            "lt-lt",
            "lt-ru",
            "lv-en",
            "lv-ru",
            "mhr-ru",
            "mrj-ru",
            "nl-en",
            "nl-ru",
            "no-en",
            "no-ru",
            "pl-ru",
            "pt-en",
            "pt-ru",
            "ru-be",
            "ru-bg",
            "ru-cs",
            "ru-da",
            "ru-de",
            "ru-el",
            "ru-en",
            "ru-es",
            "ru-et",
            "ru-fi",
            "ru-fr",
            "ru-hu",
            "ru-it",
            "ru-lt",
            "ru-lv",
            "ru-mhr",
            "ru-mrj",
            "ru-nl",
            "ru-no",
            "ru-pl",
            "ru-pt",
            "ru-pt-BR",
            "ru-ru",
            "ru-sk",
            "ru-sv",
            "ru-tr",
            "ru-tt",
            "ru-uk",
            "ru-zh",
            "sk-en",
            "sk-ru",
            "sv-en",
            "sv-ru",
            "tr-de",
            "tr-en",
            "tr-ru",
            "tt-ru",
            "uk-en",
            "uk-ru",
            "uk-uk",
            "zh-ru",
        )

        val SUPPORTED = (PREFERRED_CODES + SUPPORTED_CODES).distinct().map(::fromCode)

        val EN_RU = SUPPORTED.first { it.code == "en-ru" }

        val FILTERS = listOf(ALL) + SUPPORTED

        val SOURCE_LANGUAGES = languageOptions(
            SUPPORTED.map { it.sourceLanguage }
        )

        val TARGET_LANGUAGES = languageOptions(
            SUPPORTED.map { it.targetLanguage }
        )

        val SOURCE_LANGUAGE_FILTERS = listOf(WordLanguage.ALL) + SOURCE_LANGUAGES

        fun byCode(code: String): WordLanguagePair {
            return FILTERS.firstOrNull { it.code == code } ?: EN_RU
        }

        fun byLanguages(
            sourceLanguage: String,
            targetLanguage: String,
        ): WordLanguagePair? {
            return SUPPORTED.firstOrNull { pair ->
                pair.sourceLanguage == sourceLanguage && pair.targetLanguage == targetLanguage
            }
        }

        fun languageByCode(code: String): WordLanguage {
            return WordLanguage(
                code = code,
                title = languageName(code),
            )
        }

        fun targetLanguagesFor(sourceLanguage: WordLanguage): List<WordLanguage> {
            val targetLanguageCodes = if (sourceLanguage.isAll) {
                SUPPORTED.map { it.targetLanguage }
            } else {
                SUPPORTED
                    .filter { it.sourceLanguage == sourceLanguage.code }
                    .map { it.targetLanguage }
            }

            return languageOptions(targetLanguageCodes)
        }

        fun targetLanguageFiltersFor(sourceLanguage: WordLanguage): List<WordLanguage> {
            return listOf(WordLanguage.ALL) + targetLanguagesFor(sourceLanguage)
        }

        fun matches(
            word: Word,
            sourceLanguage: WordLanguage,
            targetLanguage: WordLanguage,
        ): Boolean {
            return sourceLanguage.matchesSource(word) && targetLanguage.matchesTarget(word)
        }

        private fun fromCode(code: String): WordLanguagePair {
            val sourceLanguage = LANGUAGE_CODES.first { language ->
                code.startsWith("$language-")
            }
            val targetLanguage = code.removePrefix("$sourceLanguage-")

            return WordLanguagePair(
                code = code,
                sourceLanguage = sourceLanguage,
                targetLanguage = targetLanguage,
                title = "${languageName(sourceLanguage)} -> ${languageName(targetLanguage)}",
            )
        }

        private fun languageName(code: String): String {
            return LANGUAGE_NAMES[code] ?: code.uppercase()
        }

        private fun languageOptions(codes: List<String>): List<WordLanguage> {
            return codes
                .distinct()
                .map(::languageByCode)
        }
    }
}
