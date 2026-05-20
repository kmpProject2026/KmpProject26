package com.itis.kmpproj26.feature.auth.ui.screen.splash

sealed class SplashAction {

    data object NavigateToLogin : SplashAction()
    data object NavigateToProfile : SplashAction()
}