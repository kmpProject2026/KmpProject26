package com.itis.kmpproj26.feature.words.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class WordsRoute : NavKey {

    @Serializable
    data object List : WordsRoute()

    @Serializable
    data object Add : WordsRoute()
}
