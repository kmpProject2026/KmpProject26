package com.itis.kmpproj26.feature.auth.ui.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.itis.kmpproj26.common.navigation.Navigator
import com.itis.kmpproj26.common.navigation.Route
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginScreen
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationScreen
import com.itis.kmpproj26.feature.auth.ui.screen.splash.SplashScreen

fun EntryProviderScope<NavKey>.authEntryBuilder(
    navigator: Navigator,
) {

    entry<Route.Auth> {
        SplashScreen(
            navigateToLogin = {
                navigator.navigateClearingStack(AuthRoute.Login)
            },
            navigateToProfile = {
                navigator.navigateClearingStack(Route.Profile)
            }
        )
    }

    entry<AuthRoute.Login> {
        LoginScreen(
            navigateToRegistration = {
                navigator.navigateClearingStack(AuthRoute.Registration)
            },
            navigateToProfile = {
                navigator.navigateClearingStack(Route.Profile)
            }
        )
    }

    entry<AuthRoute.Registration> {
        RegistrationScreen(
            navigateToLogin = {
                navigator.navigateClearingStack(AuthRoute.Login)
            },
            navigateToProfile = {
                navigator.navigateClearingStack(Route.Profile)
            }
        )
    }
}
