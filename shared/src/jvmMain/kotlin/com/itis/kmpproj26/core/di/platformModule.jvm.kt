package com.itis.kmpproj26.core.di

import com.itis.kmpproj26.core.analytics.AnalyticsService
import com.itis.kmpproj26.core.analytics.NoOpAnalyticsService
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<AnalyticsService> {
            NoOpAnalyticsService()
        }
    }
