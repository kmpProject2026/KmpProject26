package com.itis.kmpproj26.feature.auth.ui.screen.registration.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationViewModel

class RegistrationEmailChangedEventHandler () :
    BaseEventHandler<RegistrationEvent.OnEmailChanged, RegistrationViewModel>() {

    override fun RegistrationViewModel.obtain(event: RegistrationEvent.OnEmailChanged) {
        viewState = viewState.copy(
            email = event.email.trim(),
            emailError = null,
            registrationError = null
        )
    }
}
