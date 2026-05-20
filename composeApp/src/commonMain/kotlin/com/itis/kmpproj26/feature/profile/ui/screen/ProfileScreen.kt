package com.itis.kmpproj26.feature.profile.ui.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.kmpproj26.common.ui.component.DDialog
import com.itis.kmpproj26.feature.profile.ui.screen.component.ProfileContent
import com.itis.kmpproj26.feature.profile.ui.screen.component.ProfileShimmer
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_failure_unknown
import kmpproj26native.composeapp.generated.resources.common_no
import kmpproj26native.composeapp.generated.resources.common_warning
import kmpproj26native.composeapp.generated.resources.common_yes
import kmpproj26native.composeapp.generated.resources.profile_delete_profile_warning
import kmpproj26native.composeapp.generated.resources.profile_deleted
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileScreen(
    navigateToAuth: () -> Unit,
) {

    val viewModel = retain { ProfileViewModel() }
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    val profileDeletedRes = stringResource(Res.string.profile_deleted)

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(ProfileEvent.Init)
    }

    LaunchedEffect(Unit) {
        viewModel.viewActions.collect { action ->
            when (action) {

                is ProfileAction.NavigateToAuth ->
                    navigateToAuth()

                is ProfileAction.ShowToast -> {
                    val message = when (action.toast) {
                        ProfileToast.PROFILE_DELETED -> profileDeletedRes
                    }
                    snackbarHostState.showSnackbar(message)
                }
            }
        }
    }

    when {
        state.isLoading -> ProfileShimmer()
        else -> ProfileContent(
            state = state,
            onLogoutClick = {
                viewModel.obtainEvent(ProfileEvent.OnLogoutClick)
            },
            onDeleteProfileClick = {
                viewModel.obtainEvent(ProfileEvent.OnDeleteProfileClick)
            }
        )
    }

    when (state.dialog) {
        ProfileDialog.ERROR_UNKNOWN -> DDialog(
            message = stringResource(Res.string.common_failure_unknown),
            onDismiss = {
                viewModel.obtainEvent(ProfileEvent.HideErrorDialog)
            }
        )
        ProfileDialog.CONFIRM_DELETE -> DDialog(
            title = stringResource(Res.string.common_warning),
            message = stringResource(Res.string.profile_delete_profile_warning),
            confirmButtonText = stringResource(Res.string.common_yes),
            dismissButtonText = stringResource(Res.string.common_no),
            onConfirm = {
                viewModel.obtainEvent(ProfileEvent.OnDeleteProfileConfirmed)
            },
            onDismiss = {
                viewModel.obtainEvent(ProfileEvent.OnDeleteProfileCancelled)
            }
        )
        null -> Unit
    }
}
