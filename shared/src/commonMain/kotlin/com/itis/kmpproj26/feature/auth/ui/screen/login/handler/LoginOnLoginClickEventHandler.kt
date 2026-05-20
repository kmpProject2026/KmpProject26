package com.itis.kmpproj26.feature.auth.ui.screen.login.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.util.result.FailureReason
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.usecase.LoginUseCase
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginDialog
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginAction
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginError
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginEvent
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginViewModel
import com.itis.kmpproj26.feature.auth.ui.validator.EmailValidator
import com.itis.kmpproj26.feature.auth.ui.validator.PasswordValidator
import kotlinx.coroutines.launch

class LoginOnLoginClickEventHandler(
    private val loginUseCase: LoginUseCase,
) : BaseEventHandler<LoginEvent.OnLoginClick, LoginViewModel>() {

    override fun LoginViewModel.obtain(event: LoginEvent.OnLoginClick) {
        val emailError = EmailValidator.validate(viewState.email)
        val passwordError = PasswordValidator.validate(viewState.password)
        if (emailError != null || passwordError != null) {
            viewState = viewState.copy(
                emailError = emailError,
                passwordError = passwordError
            )
            return
        }
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            when (val result = loginUseCase(
                email = viewState.email,
                password = viewState.password
            )) {
                is Result.Success -> {
                    viewState = viewState.copy(isLoading = false)
                    viewAction = LoginAction.NavigateToProfile
                }

                is Result.Failure -> onFailure(result.reason)
            }
        }
    }

    private fun LoginViewModel.onFailure(failureReason: FailureReason) {
        viewState = when (failureReason) {
            is FailureReason.BadRequest -> {
                viewState.copy(
                    isLoading = false,
                    loginError = LoginError.INVALID,
                )
            }

            else -> {
                viewState.copy(
                    isLoading = false,
                    dialog = LoginDialog.ERROR_UNKNOWN,
                )
            }
        }
    }
}
