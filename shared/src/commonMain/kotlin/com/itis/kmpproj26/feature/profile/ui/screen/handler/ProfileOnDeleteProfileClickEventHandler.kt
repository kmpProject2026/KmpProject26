package com.itis.kmpproj26.feature.profile.ui.screen.handler

import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileDialog
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileEvent
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileViewModel

class ProfileOnDeleteProfileClickEventHandler:
    BaseEventHandler<ProfileEvent.OnDeleteProfileClick, ProfileViewModel>() {

    override fun ProfileViewModel.obtain(event: ProfileEvent.OnDeleteProfileClick) {
        viewState = viewState.copy(
            dialog = ProfileDialog.CONFIRM_DELETE,
        )
    }
}

