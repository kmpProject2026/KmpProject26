package com.itis.kmpproj26.common.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun NavHost(
    entryBuilders: Set<NavEntryBuilder>,
) {
    val backStack = rememberNavBackStack(
        configuration = NavigationConfiguration.savedStateConfiguration,
        Route.Auth
    )
    val navState = remember { NavigationState(backStacks = backStack) }
    val navigator = remember { Navigator(navState) }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                navState = navState,
                navigator = navigator,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavDisplay(
                backStack = navState.stacksInUse,
                entryProvider = entryProvider {
                    entryBuilders.forEach { builder ->
                        builder(navigator)
                    }
                },
            )
        }
    }
}
