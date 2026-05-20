package com.itis.kmpproj26.feature.auth.ui.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.kmpproj26.common.ui.component.DDialog
import com.itis.kmpproj26.feature.auth.ui.screen.login.component.LoginContent
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_failure_unknown
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    navigateToProfile: () -> Unit,
    navigateToRegistration: () -> Unit,
) {

    val viewModel = retain { LoginViewModel() }
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(LoginEvent.Init)
    }

    LaunchedEffect(Unit) {
        viewModel.viewActions.collect { action ->
            when (action) {
                LoginAction.NavigateToProfile -> navigateToProfile()
                LoginAction.NavigateToRegistration -> navigateToRegistration()
            }
        }
    }

    LoginContent(
        state = state,
        onEmailChanged = { viewModel.obtainEvent(LoginEvent.OnEmailChanged(it)) },
        onPasswordChanged = { viewModel.obtainEvent(LoginEvent.OnPasswordChanged(it)) },
        onLoginClick = { viewModel.obtainEvent(LoginEvent.OnLoginClick) },
        onRegisterClick = { viewModel.obtainEvent(LoginEvent.OnRegisterClick) },
    )

    if (state.showErrorDialog) {
        DDialog(
            message = stringResource(Res.string.common_failure_unknown),
            onDismiss = {
                viewModel.obtainEvent(LoginEvent.HideErrorDialog)
            }
        )
    }
}
