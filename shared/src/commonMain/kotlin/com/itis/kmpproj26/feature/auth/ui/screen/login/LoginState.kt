package com.itis.kmpproj26.feature.auth.ui.screen.login

import com.itis.kmpproj26.feature.auth.ui.validator.EmailError
import com.itis.kmpproj26.feature.auth.ui.validator.PasswordError

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val emailError: EmailError? = null,
    val passwordError: PasswordError? = null,
    val loginError: LoginError? = null,
    val errorDialog: LoginErrorDialog? = null,
)

enum class LoginError {
    INVALID,
}

enum class LoginErrorDialog {
    UNKNOWN
}
