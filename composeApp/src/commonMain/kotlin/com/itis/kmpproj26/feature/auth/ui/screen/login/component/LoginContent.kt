package com.itis.kmpproj26.feature.auth.ui.screen.login.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.itis.kmpproj26.common.ui.component.DButton
import com.itis.kmpproj26.common.ui.component.DOutlinedInputSection
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.common.ui.theme.designsystem.SeedCream
import com.itis.kmpproj26.feature.auth.ui.helper.toStringRes
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginError
import com.itis.kmpproj26.feature.auth.ui.screen.login.LoginState
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.app_name
import kmpproj26native.composeapp.generated.resources.auth_email
import kmpproj26native.composeapp.generated.resources.auth_enter_email_label
import kmpproj26native.composeapp.generated.resources.auth_enter_password_label
import kmpproj26native.composeapp.generated.resources.auth_error_invalid_data
import kmpproj26native.composeapp.generated.resources.auth_login
import kmpproj26native.composeapp.generated.resources.auth_password
import kmpproj26native.composeapp.generated.resources.auth_register
import kmpproj26native.composeapp.generated.resources.ic_dictionary
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginContent(
    state: LoginState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = Providers.spacing.l),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(Providers.spacing.xxl))

        Box(
            modifier = Modifier.size(Providers.componentSize.iconExtraLarge)
                .border(
                    width = Providers.spacing.xxxs,
                    color = Providers.color.primary,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .background(SeedCream),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_dictionary),
                contentDescription = stringResource(Res.string.app_name),
                modifier = Modifier.size(Providers.componentSize.iconLarge * 1.4f)
                    .padding(Providers.spacing.m),
                contentScale = ContentScale.Inside
            )
        }

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        DText(
            text = stringResource(Res.string.app_name),
            style = Providers.typography.heading5,
            color = Providers.color.primary,
        )

        Spacer(modifier = Modifier.height(Providers.spacing.xxl))

        DOutlinedInputSection(
            title = stringResource(Res.string.auth_email),
            value = state.email,
            onValueChange = onEmailChanged,
            placeholder = stringResource(Res.string.auth_enter_email_label),
            isError = state.emailError != null || state.loginError != null,
            errorText = state.emailError?.toStringRes(),
        )

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        DOutlinedInputSection(
            title = stringResource(Res.string.auth_password),
            value = state.password,
            onValueChange = onPasswordChanged,
            placeholder = stringResource(Res.string.auth_enter_password_label),
            isError = state.passwordError != null || state.loginError != null,
            errorText = state.passwordError?.toStringRes() ?: state.loginError?.let {
                when (it) {
                    LoginError.INVALID -> stringResource(Res.string.auth_error_invalid_data)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(Providers.spacing.xl))

        DButton(
            text = stringResource(Res.string.auth_login),
            onClick = onLoginClick,
            enabled = !state.isLoading,
        )

        Spacer(modifier = Modifier.height(Providers.spacing.s))

        DButton(
            text = stringResource(Res.string.auth_register),
            onClick = onRegisterClick,
            enabled = !state.isLoading,
            containerColor = Providers.color.secondaryContainer,
            contentColor = Providers.color.onSecondaryContainer
        )

        Spacer(modifier = Modifier.height(Providers.spacing.xxl))
    }
}
