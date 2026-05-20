package com.itis.kmpproj26.feature.auth.ui.screen.login.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginEvent
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginViewModel

class LoginHideErrorDialogEventHandler :
    BaseEventHandler<LoginEvent.HideErrorDialog, LoginViewModel>() {

    override fun LoginViewModel.obtain(event: LoginEvent.HideErrorDialog) {
        viewState = viewState.copy(errorDialog = null)
    }
}
