package com.itis.kmpproj26.feature.profile.ui.screen.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileEvent
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileViewModel

class ProfileOnDeleteProfileCancelledEventHandler :
    BaseEventHandler<ProfileEvent.OnDeleteProfileCancelled, ProfileViewModel>() {

    override fun ProfileViewModel.obtain(event: ProfileEvent.OnDeleteProfileCancelled) {
        viewState = viewState.copy(dialog = null)
    }
}

