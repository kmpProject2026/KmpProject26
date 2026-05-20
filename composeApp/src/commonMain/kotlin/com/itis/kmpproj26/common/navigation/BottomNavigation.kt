package com.itis.kmpproj26.common.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavigation(
    navState: NavigationState,
    navigator: Navigator,
) {
    val currentBottomNavItem = (navState.stacksInUse.lastOrNull() as? Route)
        ?.let(BottomNavItem::findBottomNavItem)
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
                    if (!isSelected) {
                        navigator.navigateClearingStack(bottomNavItem.route)
                    }
                }
            )
        }
    }
}
