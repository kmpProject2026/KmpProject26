package com.itis.kmpproj26.feature.auth.ui.screen.login.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginEvent
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginViewModel


class LoginEmailChangedEventHandler :
    BaseEventHandler<LoginEvent.OnEmailChanged, LoginViewModel>() {

    override fun LoginViewModel.obtain(event: LoginEvent.OnEmailChanged) {
        viewState = viewState.copy(
            email = event.email.trim(),
            emailError = null,
            loginError = null
        )
    }
}
