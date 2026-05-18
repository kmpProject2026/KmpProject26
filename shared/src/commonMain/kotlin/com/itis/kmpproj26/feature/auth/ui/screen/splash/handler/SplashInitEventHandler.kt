package com.itis.kmpproj26.feature.auth.ui.screen.splash.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.usecase.IsUserLoggedInUseCase
import com.itis.kmpproj26.feature.auth.ui.screen.splash.SplashAction
import com.itis.kmpproj26.feature.auth.ui.screen.splash.SplashEvent
import com.itis.kmpproj26.feature.auth.ui.screen.splash.SplashViewModel
import kotlinx.coroutines.launch

class SplashInitEventHandler(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
) : BaseEventHandler<SplashEvent.Init, SplashViewModel>() {

    override fun SplashViewModel.obtain(event: SplashEvent.Init) {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)

            val result = isUserLoggedInUseCase()
            val action = when {
                result is Result.Success && result.data ->
                    SplashAction.NavigateToCategories

                else -> SplashAction.NavigateToLogin
            }

            viewState = viewState.copy(isLoading = false)
            viewAction = action
        }
    }
}
