package com.itis.kmpproj26.feature.auth.ui.screen.splash

import com.itis.kmpproj26.core.viewmodel.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.itis.kmpproj26.feature.auth.ui.screen.splash.handler.SplashInitEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.splash.SplashAction as Action
import com.itis.kmpproj26.feature.auth.ui.screen.splash.SplashEvent as Event
import com.itis.kmpproj26.feature.auth.ui.screen.splash.SplashViewState as State

class SplashViewModel : BaseViewModel<State, Action, Event>(
    initState = State()
), KoinComponent {

    private val initEventHandler: SplashInitEventHandler by inject()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.Init -> with(initEventHandler) { obtainEvent(event) }
        }
    }
}