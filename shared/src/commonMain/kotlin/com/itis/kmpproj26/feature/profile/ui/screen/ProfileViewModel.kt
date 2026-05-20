package com.itis.kmpproj26.feature.profile.ui.screen

sealed class ProfileAction {
    data object NavigateToAuth : ProfileAction()
    data class ShowToast(val toast: ProfileToast) : ProfileAction()
}

enum class ProfileToast {
    PROFILE_DELETED
}