package com.itis.kmpproj26.feature.words.data.datasource

import com.itis.kmpproj26.feature.words.data.datasource.remote.YandexDictionaryLookupResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class RemoteWordsDataSource(
    private val httpClient: HttpClient,
) {

    suspend fun translate(
        text: String,
        sourceLanguage: String,
        targetLanguage: String,
    ): YandexDictionaryLookupResponse {
        return httpClient.get(YANDEX_DICTIONARY_LOOKUP_URL) {
            parameter("key", YANDEX_DICTIONARY_API_KEY)
            parameter("lang", "$sourceLanguage-$targetLanguage")
            parameter("text", text)
        }.body()
    }

    private companion object {
        const val YANDEX_DICTIONARY_LOOKUP_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup"
        const val YANDEX_DICTIONARY_API_KEY = "dict.1.1.20260511T171701Z.dc22c229815628f1.e636f9e59a66324dfe568c0a333f7689f44384c4"
    }
}
