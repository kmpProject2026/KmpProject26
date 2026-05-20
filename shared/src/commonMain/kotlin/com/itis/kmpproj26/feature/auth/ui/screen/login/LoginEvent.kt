package com.itis.kmpproj26.feature.auth.ui.screen.login

sealed class LoginEvent {
    data object Init : LoginEvent()
    data class OnEmailChanged(val email: String) : LoginEvent()
    data class OnPasswordChanged(val password: String) : LoginEvent()
    data object OnLoginClick : LoginEvent()
    data object OnRegisterClick : LoginEvent()
    data object HideErrorDialog : LoginEvent()
}
