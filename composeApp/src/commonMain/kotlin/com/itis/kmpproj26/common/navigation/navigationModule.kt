package com.itis.kmpproj26.common.navigation

import com.itis.kmpproj26.feature.auth.ui.navigation.authEntryBuilder
import org.koin.dsl.module

val navigationModule = module {
    single<Set<NavEntryBuilder>> {
        setOf(
            { navigator -> authEntryBuilder(navigator) },
        )
    }
}