package com.itis.kmpproj26.core.analytics

interface IosAnalyticsLogger {
    fun logEvent(name: String)
}

object IosAnalyticsBridge {

    private var logger: IosAnalyticsLogger? = null

    fun setLogger(logger: IosAnalyticsLogger) {
        this.logger = logger
    }

    fun logEvent(name: String) {
        logger?.logEvent(name)
    }
}

class IosAnalyticsService : AnalyticsService {
    override fun logEvent(
        name: String,
        params: Map<String, String>,
    ) {
        IosAnalyticsBridge.logEvent(name)
    }
}
