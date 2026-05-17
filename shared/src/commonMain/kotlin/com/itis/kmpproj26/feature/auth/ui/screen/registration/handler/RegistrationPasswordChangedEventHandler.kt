package com.itis.kmpproj26.feature.auth.ui.screen.registration.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationViewModel

class RegistrationPasswordChangedEventHandler:
    BaseEventHandler<RegistrationEvent.OnPasswordChanged, RegistrationViewModel>() {

    override fun RegistrationViewModel.obtain(intent: RegistrationEvent.OnPasswordChanged) {
        viewState = viewState.copy(
            password = intent.password.trim(),
            passwordError = null,
            registrationError = null
        )
    }
}
