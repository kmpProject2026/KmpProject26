package com.itis.kmpproj26.feature.auth.ui.validator

enum class EmailError {

    EMPTY,
    INVALID,
}

object EmailValidator {

    fun validate(email: String): EmailError? {
        return when {
            email.isEmpty() -> EmailError.EMPTY
            !email.isValidEmail() -> EmailError.INVALID
            else -> null
        }
    }

    fun String.isValidEmail(): Boolean {
        if (isBlank()) return false

        val emailRegex = Regex(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )

        return emailRegex.matches(this)
    }
}
