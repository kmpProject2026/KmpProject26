package com.itis.kmpproj26.feature.auth.ui.screen.registration.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationViewModel

class RegistrationPasswordChangedEventHandler:
    BaseEventHandler<RegistrationEvent.OnPasswordChanged, RegistrationViewModel>() {

    override fun RegistrationViewModel.obtain(event: RegistrationEvent.OnPasswordChanged) {
        viewState = viewState.copy(
            password = event.password.trim(),
            passwordError = null,
            registrationError = null
        )
    }
}
