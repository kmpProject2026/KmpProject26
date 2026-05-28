package com.itis.kmpproj26.feature.words.ui.screen.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.kmpproj26.common.ui.component.DDialog
import com.itis.kmpproj26.feature.words.ui.screen.add.component.AddWordContent
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_failure_network
import kmpproj26native.composeapp.generated.resources.common_failure_unknown
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddWordScreen(
    navigateBack: () -> Unit,
) {
    val viewModel = retain { AddWordViewModel() }
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(AddWordEvent.Init)
    }

    LaunchedEffect(Unit) {
        viewModel.viewActions.collect { action ->
            when (action) {
                AddWordAction.NavigateBack -> navigateBack()
            }
        }
    }

    AddWordContent(
        state = state,
        onSpellingChanged = { viewModel.obtainEvent(AddWordEvent.OnSpellingChanged(it)) },
        onTranslationChanged = { viewModel.obtainEvent(AddWordEvent.OnTranslationChanged(it)) },
        onSourceLanguageSelected = { viewModel.obtainEvent(AddWordEvent.OnSourceLanguageChanged(it)) },
        onTargetLanguageSelected = { viewModel.obtainEvent(AddWordEvent.OnTargetLanguageChanged(it)) },
        onTranslateClick = { viewModel.obtainEvent(AddWordEvent.OnTranslateClick) },
        onSaveClick = { viewModel.obtainEvent(AddWordEvent.OnSaveClick) },
        onBackClick = { viewModel.obtainEvent(AddWordEvent.OnBackClick) },
    )

    when (state.errorDialog) {
        AddWordErrorDialog.NETWORK -> DDialog(
            message = stringResource(Res.string.common_failure_network),
            onDismiss = {
                viewModel.obtainEvent(AddWordEvent.HideErrorDialog)
            }
        )

        AddWordErrorDialog.UNKNOWN -> DDialog(
            message = stringResource(Res.string.common_failure_unknown),
            onDismiss = {
                viewModel.obtainEvent(AddWordEvent.HideErrorDialog)
            }
        )

        null -> Unit
    }
}
