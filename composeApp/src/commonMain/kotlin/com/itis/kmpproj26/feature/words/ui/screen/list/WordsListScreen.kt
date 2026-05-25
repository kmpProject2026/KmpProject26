package com.itis.kmpproj26.feature.words.ui.screen.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.kmpproj26.common.ui.component.DDialog
import com.itis.kmpproj26.feature.words.ui.screen.list.component.WordsListContent
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_failure_unknown
import org.jetbrains.compose.resources.stringResource

@Composable
fun WordsListScreen(
    navigateBack: () -> Unit,
    navigateToAdd: () -> Unit,
) {
    val viewModel = retain { WordsListViewModel() }
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(WordsListEvent.Init)
    }

    LaunchedEffect(Unit) {
        viewModel.viewActions.collect { action ->
            when (action) {
                WordsListAction.NavigateBack -> navigateBack()
                WordsListAction.NavigateToAdd -> navigateToAdd()
            }
        }
    }

    WordsListContent(
        state = state,
        onBackClick = { viewModel.obtainEvent(WordsListEvent.OnBackClick) },
        onAddClick = { viewModel.obtainEvent(WordsListEvent.OnAddClick) },
        onDeleteClick = { viewModel.obtainEvent(WordsListEvent.OnDeleteClick(it)) },
        onSourceLanguageFilterSelected = {
            viewModel.obtainEvent(WordsListEvent.OnSourceLanguageFilterChanged(it))
        },
        onTargetLanguageFilterSelected = {
            viewModel.obtainEvent(WordsListEvent.OnTargetLanguageFilterChanged(it))
        },
    )

    when (state.errorDialog) {
        WordsListErrorDialog.UNKNOWN -> DDialog(
            message = stringResource(Res.string.common_failure_unknown),
            onDismiss = {
                viewModel.obtainEvent(WordsListEvent.HideErrorDialog)
            }
        )

        null -> Unit
    }
}
