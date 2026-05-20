package com.itis.kmpproj26.feature.auth.ui.screen.login

sealed class LoginAction {
    data object NavigateToProfile : LoginAction()
    data object NavigateToRegistration : LoginAction()
}
