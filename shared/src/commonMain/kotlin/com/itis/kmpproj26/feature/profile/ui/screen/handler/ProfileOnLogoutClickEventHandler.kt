package com.itis.kmpproj26.feature.profile.ui.screen.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.usecase.LogoutUseCase
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileAction
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileDialog
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileEvent
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileViewModel
import kotlinx.coroutines.launch

class ProfileOnLogoutClickEventHandler(
    private val logoutUseCase: LogoutUseCase,
) : BaseEventHandler<ProfileEvent.OnLogoutClick, ProfileViewModel>() {

    override fun ProfileViewModel.obtain(event: ProfileEvent.OnLogoutClick) {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            when (logoutUseCase()) {
                is Result.Success -> {
                    viewState = viewState.copy(isLoading = false)
                    viewAction = ProfileAction.NavigateToAuth
                }

                is Result.Failure ->
                    viewState = viewState.copy(
                        dialog = ProfileDialog.ERROR_UNKNOWN
                    )
            }
        }
    }
}
