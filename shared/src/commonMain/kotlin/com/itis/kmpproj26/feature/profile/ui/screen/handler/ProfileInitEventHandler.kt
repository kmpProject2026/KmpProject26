package com.itis.kmpproj26.feature.profile.ui.screen.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.analytics.AnalyticsEvent
import com.itis.kmpproj26.core.analytics.AnalyticsService
import com.itis.kmpproj26.core.util.date.DateHelper
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.usecase.GetCurrentUserUseCase
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileDialog
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileEvent
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileViewModel
import kotlinx.coroutines.launch

class ProfileInitEventHandler(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val analyticsService: AnalyticsService,
) : BaseEventHandler<ProfileEvent.Init, ProfileViewModel>() {

    override fun ProfileViewModel.obtain(event: ProfileEvent.Init) {
        analyticsService.logEvent(AnalyticsEvent.LAUNCH_PROFILE)

        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            viewState = when (val result = getCurrentUserUseCase()) {
                is Result.Success ->
                    viewState.copy(
                        isLoading = false,
                        firstName = result.data.firstName,
                        lastName = result.data.lastName,
                        email = result.data.email,
                        createdAt = DateHelper.formatDateTime(result.data.createdAt)
                    )

                is Result.Failure ->
                    viewState.copy(
                        dialog = ProfileDialog.ERROR_UNKNOWN
                    )
            }
        }
    }
}
