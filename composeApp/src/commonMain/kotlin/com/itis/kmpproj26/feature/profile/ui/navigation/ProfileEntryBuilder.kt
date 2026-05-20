package com.itis.kmpproj26.feature.profile.ui.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.itis.kmpproj26.common.navigation.Navigator
import com.itis.kmpproj26.common.navigation.Route
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileScreen

fun EntryProviderScope<NavKey>.profileEntryBuilder(
    navigator: Navigator,
) {
    entry<Route.Profile> {
        ProfileScreen(
            navigateToAuth = { navigator.navigateClearingStack(Route.Auth) },
        )
    }
}
