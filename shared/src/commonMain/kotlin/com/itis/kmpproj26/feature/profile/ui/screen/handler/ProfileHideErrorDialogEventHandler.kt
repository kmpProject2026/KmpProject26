package com.itis.kmpproj26.feature.profile.ui.screen.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileAction
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileEvent
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileViewModel

class ProfileHideErrorDialogEventHandler :
    BaseEventHandler<ProfileEvent.HideErrorDialog, ProfileViewModel>() {

    override fun ProfileViewModel.obtain(event: ProfileEvent.HideErrorDialog) {
        viewState = viewState.copy(dialog = null)
        viewAction = ProfileAction.NavigateToAuth
    }
}
