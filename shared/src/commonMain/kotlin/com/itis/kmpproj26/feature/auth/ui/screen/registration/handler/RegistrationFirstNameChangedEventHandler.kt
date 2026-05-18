package com.itis.kmpproj26.feature.auth.ui.screen.registration.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationViewModel

class RegistrationFirstNameChangedEventHandler :
    BaseEventHandler<RegistrationEvent.OnFirstNameChanged, RegistrationViewModel>() {

    override fun RegistrationViewModel.obtain(event: RegistrationEvent.OnFirstNameChanged) {
        viewState = viewState.copy(
            firstName = event.firstName,
            firstNameError = null,
            registrationError = null
        )
    }
}
