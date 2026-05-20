package com.itis.kmpproj26.common.navigation

import com.itis.kmpproj26.feature.auth.ui.navigation.authEntryBuilder
import com.itis.kmpproj26.feature.profile.ui.navigation.profileEntryBuilder
import org.koin.dsl.module

val navigationModule = module {
    single<Set<NavEntryBuilder>> {
        setOf(
            { navigator -> authEntryBuilder(navigator) },
            { navigator -> profileEntryBuilder(navigator) }
        )
    }
}