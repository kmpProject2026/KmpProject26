package com.itis.kmpproj26.feature.auth.ui.screen.registration

sealed class RegistrationAction {
    data object NavigateToProfile : RegistrationAction()
    data object NavigateToLogin : RegistrationAction()
}
