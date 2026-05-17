package com.itis.kmpproj26.feature.auth.ui.validator

enum class PasswordError {

    EMPTY,
    SHORT,
}

object PasswordValidator {

    private const val MIN_PASSWORD_LENGTH = 6

    fun validate(password: String): PasswordError? {
        return when {
            password.isEmpty() -> PasswordError.EMPTY
            password.length < MIN_PASSWORD_LENGTH -> PasswordError.SHORT
            else -> null
        }
    }
}
