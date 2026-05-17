package com.itis.kmpproj26.feature.auth.ui.screen.registration.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationViewModel

class RegistrationLastNameChangedEventHandler :
    BaseEventHandler<RegistrationEvent.OnLastNameChanged, RegistrationViewModel>() {

    override fun RegistrationViewModel.obtain(intent: RegistrationEvent.OnLastNameChanged) {
       viewState = viewState.copy(
           lastName = intent.lastName,
           lastNameError = null,
           registrationError = null
       )
    }
}
