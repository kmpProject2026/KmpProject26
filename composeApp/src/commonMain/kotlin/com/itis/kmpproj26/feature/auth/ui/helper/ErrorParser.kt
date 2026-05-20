package com.itis.kmpproj26.feature.auth.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect
import com.itis.kmpproj26.feature.auth.ui.validator.EmailError
import com.itis.kmpproj26.feature.auth.ui.validator.PasswordError
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.auth_error_empty_email
import kmpproj26native.composeapp.generated.resources.auth_error_empty_password
import kmpproj26native.composeapp.generated.resources.auth_error_invalid_email
import kmpproj26native.composeapp.generated.resources.auth_error_short_password
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmailError.toStringRes(): String {
    return when(this) {
        EmailError.EMPTY -> stringResource(Res.string.auth_error_empty_email)
        EmailError.INVALID -> stringResource(Res.string.auth_error_invalid_email)
    }
}

@Composable
fun PasswordError.toStringRes(): String {
    return when(this) {
        PasswordError.EMPTY -> stringResource(Res.string.auth_error_empty_password)
        PasswordError.SHORT -> stringResource(Res.string.auth_error_short_password)
    }
}
