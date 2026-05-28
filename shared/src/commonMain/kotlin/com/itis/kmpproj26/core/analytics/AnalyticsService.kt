package com.itis.kmpproj26.core.analytics

interface AnalyticsService {
    fun logEvent(
        name: String,
        params: Map<String, String> = emptyMap(),
    )
}
