package com.itis.kmpproj26.feature.auth.ui.screen.registration

sealed class RegistrationEvent {
    data object Init : RegistrationEvent()
    data class OnFirstNameChanged(val firstName: String) : RegistrationEvent()
    data class OnLastNameChanged(val lastName: String) : RegistrationEvent()
    data class OnEmailChanged(val email: String) : RegistrationEvent()
    data class OnPasswordChanged(val password: String) : RegistrationEvent()
    data object OnRegisterClick : RegistrationEvent()
    data object OnLoginClick : RegistrationEvent()
    data object HideErrorDialog : RegistrationEvent()
}
