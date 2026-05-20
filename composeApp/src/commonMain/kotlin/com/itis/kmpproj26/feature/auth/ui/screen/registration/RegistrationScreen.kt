package com.itis.kmpproj26.feature.auth.ui.screen.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.kmpproj26.common.ui.component.DDialog
import com.itis.kmpproj26.feature.auth.ui.screen.registration.component.RegistrationContent
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_failure_unknown
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegistrationScreen(
    navigateToProfile: () -> Unit,
    navigateToLogin: () -> Unit,
) {

    val viewModel = retain { RegistrationViewModel() }
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(RegistrationEvent.Init)
    }

    LaunchedEffect(Unit) {
        viewModel.viewActions.collect { action ->
            when (action) {
                RegistrationAction.NavigateToProfile -> navigateToProfile()
                RegistrationAction.NavigateToLogin -> navigateToLogin()
            }
        }
    }

    RegistrationContent(
        state = state,
        onFirstNameChanged = { viewModel.obtainEvent(RegistrationEvent.OnFirstNameChanged(it)) },
        onLastNameChanged = { viewModel.obtainEvent(RegistrationEvent.OnLastNameChanged(it)) },
        onEmailChanged = { viewModel.obtainEvent(RegistrationEvent.OnEmailChanged(it)) },
        onPasswordChanged = { viewModel.obtainEvent(RegistrationEvent.OnPasswordChanged(it)) },
        onLoginClick = { viewModel.obtainEvent(RegistrationEvent.OnLoginClick) },
        onRegisterClick = { viewModel.obtainEvent(RegistrationEvent.OnRegisterClick) },
    )

    when (state.errorDialog) {
        RegistrationErrorDialog.UNKNOWN -> DDialog(
            message = stringResource(Res.string.common_failure_unknown),
            onDismiss = {
                viewModel.obtainEvent(RegistrationEvent.HideErrorDialog)
            }
        )
        null -> Unit
    }
}
