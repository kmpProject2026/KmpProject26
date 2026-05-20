package com.itis.kmpproj26.feature.auth.ui.screen.login

import com.itis.kmpproj26.core.viewmodel.BaseViewModel
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginEmailChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginInitEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginOnLoginClickEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginOnRegisterClickIntentHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginPasswordChangedIntentHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginAction as Action
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginEvent as Event
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginState as State

class LoginViewModel : BaseViewModel<State, Action, Event>(
    initState = State()
), KoinComponent {

    private val initEventHandler: LoginInitEventHandler by inject()
    private val onEmailChangedEventHandler: LoginEmailChangedEventHandler by inject()
    private val onPasswordChangedEventHandler: LoginPasswordChangedIntentHandler by inject()
    private val onLoginClickEventHandler: LoginOnLoginClickEventHandler by inject()
    private val onRegisterClickEventHandler: LoginOnRegisterClickIntentHandler by inject()
    private val hideErrorDialogEventHandler: LoginHideErrorDialogEventHandler by inject()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.HideErrorDialog -> with(hideErrorDialogEventHandler) { obtain(event) }
            is Event.Init -> with(initEventHandler) { obtain(event) }
            is Event.OnEmailChanged -> with(onEmailChangedEventHandler) { obtain(event) }
            is Event.OnLoginClick -> with(onLoginClickEventHandler) { obtain(event) }
            is Event.OnPasswordChanged -> with(onPasswordChangedEventHandler) { obtain(event) }
            is Event.OnRegisterClick -> with(onRegisterClickEventHandler) { obtain(event) }
        }
    }
}