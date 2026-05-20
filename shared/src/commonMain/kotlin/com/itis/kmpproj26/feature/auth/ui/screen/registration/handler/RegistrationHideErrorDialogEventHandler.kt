package com.itis.kmpproj26.feature.auth.ui.screen.registration.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationViewModel

class RegistrationHideErrorDialogEventHandler :
    BaseEventHandler<RegistrationEvent.HideErrorDialog, RegistrationViewModel>() {

    override fun RegistrationViewModel.obtain(event: RegistrationEvent.HideErrorDialog) {
        viewState = viewState.copy(dialog = null)
    }
}
