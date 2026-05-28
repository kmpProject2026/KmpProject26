package com.itis.kmpproj26.core.analytics

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

class AndroidFirebaseAnalyticsService(
    context: Context,
) : AnalyticsService {

    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    private val crashlytics = FirebaseCrashlytics.getInstance()

    init {
        firebaseAnalytics.setAnalyticsCollectionEnabled(true)
        crashlytics.setCrashlyticsCollectionEnabled(true)
    }

    override fun logEvent(
        name: String,
        params: Map<String, String>,
    ) {
        Log.d(TAG, "Log Firebase event: $name, params: $params")
        firebaseAnalytics.logEvent(
            name,
            params.takeIf { it.isNotEmpty() }?.toBundle(),
        )
        crashlytics.log(name)
    }

    private fun Map<String, String>.toBundle() = Bundle().apply {
        forEach { (key, value) ->
            putString(key, value)
        }
    }

    private companion object {
        const val TAG = "AppAnalytics"
    }
}
