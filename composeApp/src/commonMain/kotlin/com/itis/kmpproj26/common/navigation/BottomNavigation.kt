package com.itis.kmpproj26.common.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import com.itis.kmpproj26.feature.words.ui.navigation.WordsRoute
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavigation(
    navState: NavigationState,
    navigator: Navigator,
) {
    val currentRoute = navState.stacksInUse.lastOrNull()
    val currentBottomNavItem = currentRoute
        ?.let(::findBottomNavItem)
        ?: return

    NavigationBar {
        BottomNavItem.tabs.forEach { bottomNavItem ->
            val isSelected = currentBottomNavItem == bottomNavItem

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(bottomNavItem.iconRes),
                        contentDescription = stringResource(bottomNavItem.titleRes)
                    )
                },
                label = { Text(text = stringResource(bottomNavItem.titleRes)) },
                selected = isSelected,
                onClick = {
                    if (!isSelected || currentRoute != bottomNavItem.route) {
                        navigator.navigateClearingStack(bottomNavItem.route)
                    }
                }
            )
        }
    }
}

private fun findBottomNavItem(route: NavKey): BottomNavItem? {
    return when (route) {
        is Route -> BottomNavItem.findBottomNavItem(route)
        is WordsRoute -> BottomNavItem.Words
        else -> null
    }
}
