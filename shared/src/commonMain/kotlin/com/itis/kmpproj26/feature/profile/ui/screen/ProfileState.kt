package com.itis.kmpproj26.feature.profile.ui.screen

data class ProfileState(
    val isLoading: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val createdAt: String = "",
    val dialog: ProfileDialog? = null,
)

enum class ProfileDialog {
    ERROR_UNKNOWN,
    CONFIRM_DELETE,
}