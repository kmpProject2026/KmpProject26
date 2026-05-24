package com.itis.kmpproj26.common.navigation

import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.ic_dictionary
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
        iconRes = Res.drawable.ic_dictionary,
        titleRes = Res.string.navigation_words_tab_title
    )

    companion object Companion {
        val tabs = listOf(Words)

        fun findBottomNavItem(route: Route): BottomNavItem? {
            return tabs.find { it.route::class == route::class }
        }
    }
}
