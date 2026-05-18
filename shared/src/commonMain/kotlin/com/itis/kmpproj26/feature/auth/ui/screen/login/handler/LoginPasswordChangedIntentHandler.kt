package com.itis.kmpproj26.feature.auth.ui.screen.login.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginEvent
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginViewModel


class LoginPasswordChangedIntentHandler :
    BaseEventHandler<LoginEvent.OnPasswordChanged, LoginViewModel>() {

    override fun LoginViewModel.obtain(event: LoginEvent.OnPasswordChanged) {
        viewState = viewState.copy(
            password = event.password.trim(),
            passwordError = null,
            loginError = null
        )
    }
}
