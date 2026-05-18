package com.itis.kmpproj26.feature.auth.ui.screen.registration.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationViewModel

class RegistrationInitEventHandler :
    BaseEventHandler<RegistrationEvent.Init, RegistrationViewModel>() {

    override fun RegistrationViewModel.obtain(event: RegistrationEvent.Init) {
        // analyticsService.registrationOpen()
    }
}
