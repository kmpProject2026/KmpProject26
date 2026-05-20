package com.itis.kmpproj26.feature.profile.ui.screen

import com.itis.kmpproj26.core.viewmodel.BaseViewModel
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileInitEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileOnDeleteProfileCancelledEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileOnDeleteProfileClickEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileOnDeleteProfileConfirmedEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.handler.ProfileOnLogoutClickEventHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileAction as Action
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileEvent as Event
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileState as State

class ProfileViewModel : BaseViewModel<State, Action, Event>(
    initState = State()
), KoinComponent {

    private val initEventHandler: ProfileInitEventHandler by inject()
    private val onLogoutClickEventHandler: ProfileOnLogoutClickEventHandler by inject()
    private val onDeleteProfileClickEventHandler: ProfileOnDeleteProfileClickEventHandler by inject()
    private val onDeleteProfileConfirmedEventHandler: ProfileOnDeleteProfileConfirmedEventHandler by inject()
    private val onDeleteProfileCancelledEventHandler: ProfileOnDeleteProfileCancelledEventHandler by inject()
    private val hideErrorDialogEventHandler: ProfileHideErrorDialogEventHandler by inject()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.Init -> with(initEventHandler) { obtain(event) }
            is Event.HideErrorDialog -> with(hideErrorDialogEventHandler) { obtain(event) }
            is Event.OnDeleteProfileCancelled -> with(onDeleteProfileCancelledEventHandler) { obtain(event) }
            is Event.OnDeleteProfileClick -> with(onDeleteProfileClickEventHandler) { obtain(event) }
            is Event.OnDeleteProfileConfirmed -> with(onDeleteProfileConfirmedEventHandler) { obtain(event) }
            is Event.OnLogoutClick -> with(onLogoutClickEventHandler) { obtain(event) }
        }
    }
}