package com.itis.kmpproj26.feature.words.data.datasource.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YandexDictionaryLookupResponse(
    @SerialName("head")
    val head: YandexDictionaryHead = YandexDictionaryHead(),
    @SerialName("def")
    val definitions: List<YandexDictionaryDefinition> = emptyList(),
    @SerialName("code")
    val code: Int? = null,
    @SerialName("nmt_code")
    val nmtCode: Int? = null,
    @SerialName("message")
    val message: String? = null,
)

@Serializable
data class YandexDictionaryHead(
    @SerialName("text")
    val text: String? = null,
)

@Serializable
data class YandexDictionaryDefinition(
    @SerialName("text")
    val text: String? = null,
    @SerialName("pos")
    val partOfSpeech: String? = null,
    @SerialName("ts")
    val transcription: String? = null,
    @SerialName("tr")
    val translations: List<YandexDictionaryTranslation> = emptyList(),
)

@Serializable
data class YandexDictionaryTranslation(
    @SerialName("text")
    val text: String? = null,
    @SerialName("pos")
    val partOfSpeech: String? = null,
    @SerialName("gen")
    val gender: String? = null,
    @SerialName("asp")
    val aspect: String? = null,
    @SerialName("fr")
    val frequency: Int? = null,
    @SerialName("syn")
    val synonyms: List<YandexDictionaryTextValue> = emptyList(),
    @SerialName("mean")
    val meanings: List<YandexDictionaryTextValue> = emptyList(),
    @SerialName("ex")
    val examples: List<YandexDictionaryExample> = emptyList(),
)

@Serializable
data class YandexDictionaryTextValue(
    @SerialName("text")
    val text: String? = null,
    @SerialName("pos")
    val partOfSpeech: String? = null,
    @SerialName("gen")
    val gender: String? = null,
    @SerialName("fr")
    val frequency: Int? = null,
)

@Serializable
data class YandexDictionaryExample(
    @SerialName("text")
    val text: String? = null,
    @SerialName("tr")
    val translations: List<YandexDictionaryTextValue> = emptyList(),
)

fun YandexDictionaryLookupResponse.mapToTranslation(): String? {
    if (!isSuccessful()) return null

    return definitions
        .asSequence()
        .flatMap { definition -> definition.translations.asSequence() }
        .map { translation -> translation.text }
        .mapNotNull { it?.trim()?.takeIf(String::isNotEmpty) }
        .distinct()
        .take(3)
        .joinToString(separator = ", ")
        .takeIf(String::isNotEmpty)
}

private fun YandexDictionaryLookupResponse.isSuccessful(): Boolean {
    return code == null || code == 200
}
