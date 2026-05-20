package com.itis.kmpproj26.common.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.itis.kmpproj26.feature.auth.ui.navigation.AuthRoute
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

object NavigationConfiguration {

    private val serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Route.Auth::class)
            subclass(Route.Profile::class)

            subclass(AuthRoute.Login::class)
            subclass(AuthRoute.Registration::class)
        }
    }

    val savedStateConfiguration = SavedStateConfiguration(
        from = SavedStateConfiguration.DEFAULT
    ) {
        this.serializersModule = NavigationConfiguration.serializersModule
    }
}