package com.itis.kmpproj26.feature.auth.ui.screen.registration.handler

import androidx.lifecycle.viewModelScope
import com.itis.kmpproj26.core.util.result.FailureReason
import com.itis.kmpproj26.core.viewmodel.BaseEventHandler
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.auth.domain.usecase.RegisterUseCase
import com.itis.kmpproj26.feature.auth.ui.screen.registration.NameError
import com.itis.kmpproj26.feature.auth.ui.validator.EmailValidator
import com.itis.kmpproj26.feature.auth.ui.validator.PasswordValidator
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationAction
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationError
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationEvent
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationState
import com.itis.kmpproj26.feature.auth.ui.screen.registration.RegistrationViewModel
import kotlinx.coroutines.launch

class RegistrationOnRegisterClickEventHandler (
    private val registerUseCase: RegisterUseCase,
) : BaseEventHandler<RegistrationEvent.OnRegisterClick, RegistrationViewModel>() {

    override fun RegistrationViewModel.obtain(intent: RegistrationEvent.OnRegisterClick) {
        if (!validateInput()) return

        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            when (val result = registerUseCase(
                firstName = viewState.firstName,
                lastName = viewState.lastName,
                email = viewState.email,
                password = viewState.password
            )) {
                is Result.Success -> {
                    viewState = viewState.copy(isLoading = false)
                    viewAction = RegistrationAction.NavigateToProfile
                }

                is Result.Failure -> onFailure(result.reason)
            }
        }
    }

    private fun RegistrationViewModel.validateInput(): Boolean {
        val firstNameError = if (viewState.firstName.isEmpty()) NameError.EMPTY else null
        val lastNameError = if (viewState.lastName.isEmpty()) NameError.EMPTY else null
        val emailError = EmailValidator.validate(viewState.email)
        val passwordError = PasswordValidator.validate(viewState.password)

        val hasError =
            firstNameError != null ||
                    lastNameError != null ||
                    emailError != null ||
                    passwordError != null

        if (hasError) {
            viewState = viewState.copy(
                firstNameError = firstNameError,
                lastNameError = lastNameError,
                emailError = emailError,
                passwordError = passwordError,
            )
        }
        return !hasError
    }

    private fun RegistrationViewModel.onFailure(failureReason: FailureReason) {
        viewState = when (failureReason) {
            is FailureReason.Conflict -> {
                viewState.copy(
                    isLoading = false,
                    registrationError = RegistrationError.USED_EMAIL,
                )
            }

            else -> {
                viewState.copy(
                    isLoading = false,
                    showErrorDialog = true,
                )
            }
        }
    }
}
