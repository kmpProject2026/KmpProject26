package com.itis.kmpproj26.feature.auth.ui.screen.registration.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationAction
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationViewModel

class RegistrationOnLoginClickEventHandler :
    BaseEventHandler<RegistrationEvent.OnLoginClick, RegistrationViewModel>() {

    override fun RegistrationViewModel.obtain(intent: RegistrationEvent.OnLoginClick) {
        viewAction = RegistrationAction.NavigateToLogin
    }
}
