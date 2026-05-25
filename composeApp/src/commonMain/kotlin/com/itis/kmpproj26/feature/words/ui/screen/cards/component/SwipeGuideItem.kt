package com.itis.kmpproj26.feature.words.ui.screen.cards.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.ui.screen.cards.util.CardSwipeAction

@Composable
fun SwipeGuideItem(
    action: CardSwipeAction,
    activeAction: CardSwipeAction?,
    title: String,
    modifier: Modifier = Modifier,
) {
    val isActive = activeAction == action
    val backgroundColor = if (isActive) {
        Providers.color.primaryContainer
    } else {
        Providers.color.surface
    }
    val contentColor = if (isActive) {
        Providers.color.onPrimaryContainer
    } else {
        Providers.color.onSurfaceVariant
    }

    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = Providers.shape.s,
            )
            .padding(
                horizontal = Providers.spacing.s,
                vertical = Providers.spacing.xs,
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
         DText(
            text = title,
            style = Providers.typography.bodyM,
            color = contentColor,
            maxLines = 1,
        )
    }
}
