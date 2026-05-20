package com.itis.kmpproj26.feature.auth.ui.screen.registration

import com.itis.kmpproj26.feature.auth.ui.validator.EmailError
import com.itis.kmpproj26.feature.auth.ui.validator.PasswordError

data class RegistrationState(
    val isLoading: Boolean = false,
    val email: String = "",
    val firstName: String = "",
    val firstNameError: NameError? = null,
    val lastName: String = "",
    val lastNameError: NameError? = null,
    val password: String = "",
    val emailError: EmailError? = null,
    val passwordError: PasswordError? = null,
    val registrationError: RegistrationError? = null,
    val dialog: RegistrationDialog? = null,
)

enum class NameError {
    EMPTY
}

enum class RegistrationError {
    USED_EMAIL
}

enum class RegistrationDialog {
    ERROR_UNKNOWN
}
