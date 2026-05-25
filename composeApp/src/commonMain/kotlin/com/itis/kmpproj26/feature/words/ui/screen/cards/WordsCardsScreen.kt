package com.itis.kmpproj26.feature.words.ui.screen.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.kmpproj26.common.ui.component.DDialog
import com.itis.kmpproj26.feature.words.ui.screen.cards.component.WordsCardsContent
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_failure_unknown
import org.jetbrains.compose.resources.stringResource

@Composable
fun WordsCardsScreen(
    navigateToAdd: () -> Unit,
    navigateToList: () -> Unit,
) {
    val viewModel = retain { WordsCardsViewModel() }
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(WordsCardsEvent.Init)
    }

    LaunchedEffect(Unit) {
        viewModel.viewActions.collect { action ->
            when (action) {
                WordsCardsAction.NavigateToAdd -> navigateToAdd()
                WordsCardsAction.NavigateToList -> navigateToList()
            }
        }
    }

    WordsCardsContent(
        state = state,
        onRepeatClick = { viewModel.obtainEvent(WordsCardsEvent.OnRepeatClick) },
        onRememberClick = { viewModel.obtainEvent(WordsCardsEvent.OnRememberClick) },
        onAddClick = { viewModel.obtainEvent(WordsCardsEvent.OnAddClick) },
        onListClick = { viewModel.obtainEvent(WordsCardsEvent.OnListClick) },
        onSourceLanguageFilterSelected = {
            viewModel.obtainEvent(WordsCardsEvent.OnSourceLanguageFilterChanged(it))
        },
        onTargetLanguageFilterSelected = {
            viewModel.obtainEvent(WordsCardsEvent.OnTargetLanguageFilterChanged(it))
        },
    )

    when (state.errorDialog) {
        WordsCardsErrorDialog.UNKNOWN -> DDialog(
            message = stringResource(Res.string.common_failure_unknown),
            onDismiss = {
                viewModel.obtainEvent(WordsCardsEvent.HideErrorDialog)
            }
        )

        null -> Unit
    }
}
