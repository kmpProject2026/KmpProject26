package com.itis.kmpproj26.feature.profile.ui.screen

sealed class ProfileEvent {
    data object Init : ProfileEvent()
    data object OnLogoutClick : ProfileEvent()
    data object OnDeleteProfileClick : ProfileEvent()
    data object OnDeleteProfileCancelled : ProfileEvent()
    data object OnDeleteProfileConfirmed : ProfileEvent()
    data object HideErrorDialog : ProfileEvent()
}
