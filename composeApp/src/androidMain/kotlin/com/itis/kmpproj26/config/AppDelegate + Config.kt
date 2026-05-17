package com.itis.kmpproj26.config

import com.itis.kmpproj26.AppDelegate
import com.itis.kmpproj26.BuildConfig
import com.itis.kmpproj26.core.CommonKmp
import com.itis.kmpproj26.core.config.Configuration
import org.koin.android.ext.koin.androidContext

internal fun AppDelegate.initCommon() {
    val config = Configuration(
        isDebug = BuildConfig.DEBUG,
        isHttpLoggingEnabled = BuildConfig.DEBUG,
    )
    CommonKmp.initKoin(config) {
        androidContext(applicationContext)
    }
}
