package com.itis.kmpproj26.feature.auth.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class AuthRoute : NavKey {

    @Serializable
    data object Login : AuthRoute()

    @Serializable
    data object Registration : AuthRoute()
}
