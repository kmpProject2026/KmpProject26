package com.itis.kmpproj26.feature.auth.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit,
    navigateToProfile: () -> Unit,
) {

    val viewModel = retain { SplashViewModel() }
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(SplashEvent.Init)
    }

    LaunchedEffect(Unit) {
        viewModel.viewActions.collect { action ->
            when (action) {
                SplashAction.NavigateToLogin -> navigateToLogin()
                SplashAction.NavigateToProfile -> navigateToProfile()
            }
        }
    }

    when {
        state.isLoading -> null
        else -> null
    }
}
