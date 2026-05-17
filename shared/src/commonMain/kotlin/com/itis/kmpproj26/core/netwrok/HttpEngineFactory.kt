package com.itis.kmpproj26.core.netwrok

import com.itis.kmpproj26.core.config.Configuration
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect open class HttpEngineFactory() {

    fun createEngine(configuration: Configuration): HttpClientEngineFactory<HttpClientEngineConfig>
}