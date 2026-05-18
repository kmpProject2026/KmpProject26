package com.itis.kmpproj26.feature.auth.ui.screen.login.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginAction
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginEvent
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginViewModel


class LoginOnRegisterClickIntentHandler :
    BaseEventHandler<LoginEvent.OnRegisterClick, LoginViewModel>() {

    override fun LoginViewModel.obtain(event: LoginEvent.OnRegisterClick) {
        viewAction = LoginAction.NavigateToRegistration
    }
}
