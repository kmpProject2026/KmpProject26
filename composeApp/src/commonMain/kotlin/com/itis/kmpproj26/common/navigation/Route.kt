package com.itis.kmpproj26.common.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route : NavKey {

    @Serializable
    data object Auth : Route()

    @Serializable
    data object WordsCards : Route()

    @Serializable
    data object Profile : Route()
}
