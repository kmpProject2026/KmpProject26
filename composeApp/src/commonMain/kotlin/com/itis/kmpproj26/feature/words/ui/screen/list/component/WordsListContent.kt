package com.itis.kmpproj26.feature.words.ui.screen.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DIconButton
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.ui.component.LanguageDirectionPicker
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListState
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_add
import kmpproj26native.composeapp.generated.resources.common_back
import kmpproj26native.composeapp.generated.resources.ic_add
import kmpproj26native.composeapp.generated.resources.ic_back
import kmpproj26native.composeapp.generated.resources.words_empty_title
import kmpproj26native.composeapp.generated.resources.words_list_title
import kmpproj26native.composeapp.generated.resources.words_source_language
import kmpproj26native.composeapp.generated.resources.words_target_language
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.fav.yogadaily.ui.component.DIcon

@Composable
fun WordsListContent(
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
            DIconButton(onClick = onBackClick) {
                DIcon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.common_back),
                    tint = Providers.color.onSurface,
                )
            }

            Spacer(modifier = Modifier.width(Providers.spacing.xs))

            DText(
                text = stringResource(Res.string.words_list_title),
                modifier = Modifier.weight(1f),
                style = Providers.typography.heading6,
            )

            DIconButton(onClick = onAddClick) {
                DIcon(
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
