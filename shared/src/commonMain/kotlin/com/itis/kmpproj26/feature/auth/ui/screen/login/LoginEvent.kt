package com.itis.kmpproj26.feature.auth.ui.screen.login

sealed class LoginEvent {
    object Init : LoginEvent()
    data class OnEmailChanged(val email: String) : LoginEvent()
    data class OnPasswordChanged(val password: String) : LoginEvent()
    object OnLoginClick : LoginEvent()
    object OnRegisterClick : LoginEvent()
    object HideErrorDialog : LoginEvent()
}
