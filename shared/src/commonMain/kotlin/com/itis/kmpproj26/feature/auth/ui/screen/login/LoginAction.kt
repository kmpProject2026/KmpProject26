package com.itis.kmpproj26.feature.auth.ui.screen.login

sealed class LoginAction {
    object NavigateToProfile : LoginAction()
    object NavigateToRegistration : LoginAction()
}
