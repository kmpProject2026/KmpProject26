package com.itis.kmpproj26.feature.words.ui.screen.cards.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.ui.screen.cards.util.CardSwipeAction
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.words_remember
import kmpproj26native.composeapp.generated.resources.words_repeat
import org.jetbrains.compose.resources.stringResource

@Composable
fun SwipeGuide(
    activeAction: CardSwipeAction?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Providers.color.surfaceContainer,
                shape = Providers.shape.s,
            )
            .padding(Providers.spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(Providers.spacing.xs),
    ) {
        SwipeGuideItem(
            action = CardSwipeAction.Repeat,
            activeAction = activeAction,
            title = stringResource(Res.string.words_repeat),
            modifier = Modifier.weight(1f),
        )

        SwipeGuideItem(
            action = CardSwipeAction.Remember,
            activeAction = activeAction,
            title = stringResource(Res.string.words_remember),
            modifier = Modifier.weight(1f),
        )
    }
}
