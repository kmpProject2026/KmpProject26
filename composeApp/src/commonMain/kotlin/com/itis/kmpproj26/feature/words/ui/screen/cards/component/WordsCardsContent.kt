package com.itis.kmpproj26.feature.words.ui.screen.cards.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsState
import com.itis.kmpproj26.feature.words.ui.screen.cards.util.CardSwipeAction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DIconButton
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.ui.component.LanguageDirectionPicker
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_add
import kmpproj26native.composeapp.generated.resources.ic_add
import kmpproj26native.composeapp.generated.resources.ic_search
import kmpproj26native.composeapp.generated.resources.words_cards_title
import kmpproj26native.composeapp.generated.resources.words_list_title
import kmpproj26native.composeapp.generated.resources.words_source_language
import kmpproj26native.composeapp.generated.resources.words_target_language
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.fav.yogadaily.ui.component.DIcon

@Composable
fun WordsCardsContent(
    state: WordsCardsState,
    onRepeatClick: () -> Unit,
    onRememberClick: () -> Unit,
    onAddClick: () -> Unit,
    onListClick: () -> Unit,
    onSourceLanguageFilterSelected: (WordLanguage) -> Unit,
    onTargetLanguageFilterSelected: (WordLanguage) -> Unit,
) {
    val currentWord = state.currentWord
    var activeSwipeAction by remember { mutableStateOf<CardSwipeAction?>(null) }

    LaunchedEffect(currentWord, state.isLoading) {
        if (currentWord == null || state.isLoading) {
            activeSwipeAction = null
        }
    }

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

            DIconButton(onClick = onListClick) {
                DIcon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = stringResource(Res.string.words_list_title),
                    tint = Providers.color.onSurface,
                )
            }

            Spacer(modifier = Modifier.width(Providers.spacing.s))

            DIconButton(onClick = onAddClick) {
                DIcon(
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

        SwipeGuide(activeAction = activeSwipeAction)

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
                else -> SwipeableCardDeck(
                    words = state.words,
                    currentIndex = state.currentIndex,
                    onRepeatSwipe = onRepeatClick,
                    onRememberSwipe = onRememberClick,
                    onActiveSwipeActionChanged = { activeSwipeAction = it },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}