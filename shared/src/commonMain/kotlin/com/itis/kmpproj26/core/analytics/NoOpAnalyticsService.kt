package com.itis.kmpproj26.core.analytics

class NoOpAnalyticsService : AnalyticsService {
    override fun logEvent(
        name: String,
        params: Map<String, String>,
    ) = Unit
}
