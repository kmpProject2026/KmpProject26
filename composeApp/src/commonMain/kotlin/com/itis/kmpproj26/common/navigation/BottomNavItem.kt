package com.itis.kmpproj26.common.navigation

import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.ic_profile
import kmpproj26native.composeapp.generated.resources.ic_words
import kmpproj26native.composeapp.generated.resources.navigation_profile_tab_title
import kmpproj26native.composeapp.generated.resources.navigation_words_tab_title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class BottomNavItem(
    val route: Route,
    val iconRes: DrawableResource,
    val titleRes: StringResource,
) {

    data object Words : BottomNavItem(
        route = Route.WordsCards,
        iconRes = Res.drawable.ic_words,
        titleRes = Res.string.navigation_words_tab_title
    )

    data object Profile : BottomNavItem(
        route = Route.Profile,
        iconRes = Res.drawable.ic_profile,
        titleRes = Res.string.navigation_profile_tab_title
    )

    companion object Companion {
        val tabs = listOf(Words, Profile)

        fun findBottomNavItem(route: Route): BottomNavItem? {
            return tabs.find { it.route::class == route::class }
        }
    }
}
