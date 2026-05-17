package com.itis.kmpproj26.core.netwrok

import com.itis.kmpproj26.core.config.Configuration
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.config
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

actual open class HttpEngineFactory actual constructor() {
    actual fun createEngine(
        configuration: Configuration
    ): HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp.config {
        config {
            retryOnConnectionFailure(true)
        }
        if (configuration.isDebug) {
            addInterceptor(
                HttpLoggingInterceptor { Timber.tag("Network").d(it) }.apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                },
            )
        }
    }
}