package com.itis.kmpproj26.feature.auth.ui.screen.registration

import com.itis.kmpproj26.core.viewmodel.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationInitEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationFirstNameChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationLastNameChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationEmailChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationPasswordChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationOnLoginClickEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationOnRegisterClickEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationAction as Action
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent as Event
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationState as State

class RegistrationViewModel : BaseViewModel<State, Action, Event>(
    initState = State()
), KoinComponent {

    private val initEventHandler: RegistrationInitEventHandler by inject()
    private val onFirstNameChangedEventHandler: RegistrationFirstNameChangedEventHandler by inject()
    private val onLastNameChangedEventHandler: RegistrationLastNameChangedEventHandler by inject()
    private val onEmailChangedEventHandler: RegistrationEmailChangedEventHandler by inject()
    private val onPasswordChangedEventHandler: RegistrationPasswordChangedEventHandler by inject()
    private val onLoginClickEventHandler: RegistrationOnLoginClickEventHandler by inject()
    private val onRegisterClickEventHandler: RegistrationOnRegisterClickEventHandler by inject()
    private val hideErrorDialogEventHandler: RegistrationHideErrorDialogEventHandler by inject()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.HideErrorDialog -> with(hideErrorDialogEventHandler) { obtainEvent(event) }
            is Event.Init -> with(initEventHandler) { obtainEvent(event) }
            is Event.OnEmailChanged -> with(onEmailChangedEventHandler) { obtainEvent(event) }
            is Event.OnFirstNameChanged -> with(onFirstNameChangedEventHandler) { obtainEvent(event) }
            is Event.OnLastNameChanged -> with(onLastNameChangedEventHandler) { obtainEvent(event) }
            is Event.OnLoginClick -> with(onLoginClickEventHandler) { obtainEvent(event) }
            is Event.OnPasswordChanged -> with(onPasswordChangedEventHandler) { obtainEvent(event) }
            is Event.OnRegisterClick -> with(onRegisterClickEventHandler) { obtainEvent(event) }
        }
    }
}