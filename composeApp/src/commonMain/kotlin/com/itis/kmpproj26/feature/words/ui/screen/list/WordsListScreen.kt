package com.itis.kmpproj26.feature.words.ui.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.itis.kmpproj26.feature.words.domain.model.Word
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.ui.component.LanguageDirectionPicker
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_add
import kmpproj26native.composeapp.generated.resources.common_back
import kmpproj26native.composeapp.generated.resources.common_close
import kmpproj26native.composeapp.generated.resources.common_failure_unknown
import kmpproj26native.composeapp.generated.resources.ic_add
import kmpproj26native.composeapp.generated.resources.ic_back
import kmpproj26native.composeapp.generated.resources.ic_close
import kmpproj26native.composeapp.generated.resources.words_empty_title
import kmpproj26native.composeapp.generated.resources.words_list_title
import kmpproj26native.composeapp.generated.resources.words_source_language
import kmpproj26native.composeapp.generated.resources.words_target_language
import org.jetbrains.compose.resources.painterResource
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

@Composable
private fun WordsListContent(
    state: WordsListState,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    onDeleteClick: (Long) -> Unit,
    onSourceLanguageFilterSelected: (WordLanguage) -> Unit,
    onTargetLanguageFilterSelected: (WordLanguage) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Providers.spacing.l),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.common_back),
                    tint = Providers.color.onSurface,
                )
            }

            DText(
                text = stringResource(Res.string.words_list_title),
                modifier = Modifier.weight(1f),
                style = Providers.typography.heading6,
            )

            IconButton(onClick = onAddClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_add),
                    contentDescription = stringResource(Res.string.common_add),
                    tint = Providers.color.onSurface,
                )
            }
        }

        Spacer(modifier = Modifier.height(Providers.spacing.m))

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
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.words.isEmpty() -> DText(
                    text = stringResource(Res.string.words_empty_title),
                    style = Providers.typography.titleLarge,
                )

                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(Providers.spacing.s),
                ) {
                    items(
                        items = state.words,
                        key = { it.id },
                    ) { word ->
                        WordListItem(
                            word = word,
                            onDeleteClick = { onDeleteClick(word.id) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WordListItem(
    word: Word,
    onDeleteClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = Providers.shape.s,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Providers.spacing.m),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                DText(
                    text = word.spelling,
                    style = Providers.typography.titleMedium,
                )

                Spacer(modifier = Modifier.height(Providers.spacing.xxs))

                DText(
                    text = word.translation,
                    style = Providers.typography.bodyM,
                    color = Providers.color.onSurfaceVariant,
                )

                Spacer(modifier = Modifier.height(Providers.spacing.xxs))

                DText(
                    text = word.languagePairTitle,
                    style = Providers.typography.bodyS,
                    color = Providers.color.onSurfaceVariant,
                )
            }

            IconButton(onClick = onDeleteClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_close),
                    contentDescription = stringResource(Res.string.common_close),
                    tint = Providers.color.onSurfaceVariant,
                )
            }
        }
    }
}
