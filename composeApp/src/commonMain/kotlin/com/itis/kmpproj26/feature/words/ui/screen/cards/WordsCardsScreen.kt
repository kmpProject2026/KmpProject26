package com.itis.kmpproj26.feature.words.ui.screen.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.kmpproj26.common.ui.component.DDialog
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.ui.component.LanguageDirectionPicker
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_add
import kmpproj26native.composeapp.generated.resources.common_failure_unknown
import kmpproj26native.composeapp.generated.resources.ic_add
import kmpproj26native.composeapp.generated.resources.ic_search
import kmpproj26native.composeapp.generated.resources.words_cards_title
import kmpproj26native.composeapp.generated.resources.words_empty_title
import kmpproj26native.composeapp.generated.resources.words_list_title
import kmpproj26native.composeapp.generated.resources.words_remember
import kmpproj26native.composeapp.generated.resources.words_repeat
import kmpproj26native.composeapp.generated.resources.words_source_language
import kmpproj26native.composeapp.generated.resources.words_target_language
import org.jetbrains.compose.resources.painterResource
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

@Composable
private fun WordsCardsContent(
    state: WordsCardsState,
    onRepeatClick: () -> Unit,
    onRememberClick: () -> Unit,
    onAddClick: () -> Unit,
    onListClick: () -> Unit,
    onSourceLanguageFilterSelected: (WordLanguage) -> Unit,
    onTargetLanguageFilterSelected: (WordLanguage) -> Unit,
) {
    val currentWord = state.currentWord

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Providers.spacing.l),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DText(
                text = stringResource(Res.string.words_cards_title),
                modifier = Modifier.weight(1f),
                style = Providers.typography.heading6,
            )

            IconButton(onClick = onListClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = stringResource(Res.string.words_list_title),
                    tint = Providers.color.onSurface,
                )
            }

            IconButton(onClick = onAddClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_add),
                    contentDescription = stringResource(Res.string.common_add),
                    tint = Providers.color.onSurface,
                )
            }
        }

        Spacer(modifier = Modifier.height(Providers.spacing.l))

        LanguageDirectionPicker(
            sourceTitle = stringResource(Res.string.words_source_language),
            targetTitle = stringResource(Res.string.words_target_language),
            selectedSourceLanguage = state.selectedSourceLanguageFilter,
            selectedTargetLanguage = state.selectedTargetLanguageFilter,
            sourceLanguages = state.availableSourceLanguageFilters,
            targetLanguages = state.availableTargetLanguageFilters,
            onSourceLanguageSelected = onSourceLanguageFilterSelected,
            onTargetLanguageSelected = onTargetLanguageFilterSelected,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                currentWord == null -> EmptyWords(onAddClick)
                else -> Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Providers.spacing.m),
                    shape = Providers.shape.s,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Providers.spacing.l),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        DText(
                            text = currentWord.spelling,
                            style = Providers.typography.heading4,
                            color = Providers.color.primary,
                        )

                        Spacer(modifier = Modifier.height(Providers.spacing.m))

                        DText(
                            text = currentWord.translation,
                            style = Providers.typography.heading6,
                        )

                        Spacer(modifier = Modifier.height(Providers.spacing.m))

                        DText(
                            text = currentWord.languagePairTitle,
                            style = Providers.typography.bodyS,
                            color = Providers.color.onSurfaceVariant,
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Providers.spacing.s),
        ) {
            OutlinedButton(
                onClick = onRepeatClick,
                modifier = Modifier.weight(1f),
                enabled = state.words.isNotEmpty() && !state.isLoading,
            ) {
                DText(text = stringResource(Res.string.words_repeat))
            }

            Button(
                onClick = onRememberClick,
                modifier = Modifier.weight(1f),
                enabled = state.words.isNotEmpty() && !state.isLoading,
            ) {
                DText(
                    text = stringResource(Res.string.words_remember),
                    color = Providers.color.onPrimary,
                )
            }
        }
    }
}

@Composable
private fun EmptyWords(
    onAddClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DText(
            text = stringResource(Res.string.words_empty_title),
            style = Providers.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        Button(onClick = onAddClick) {
            DText(
                text = stringResource(Res.string.common_add),
                color = Providers.color.onPrimary,
            )
        }
    }
}
