package com.itis.kmpproj26.core.netwrok

import com.itis.kmpproj26.core.config.Configuration
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual open class HttpEngineFactory actual constructor() {
    actual fun createEngine(configuration: Configuration): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
}