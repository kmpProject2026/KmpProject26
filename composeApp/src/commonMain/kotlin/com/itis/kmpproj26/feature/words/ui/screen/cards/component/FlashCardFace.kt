package com.itis.kmpproj26.feature.words.ui.screen.cards.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers

@Composable
fun FlashCardFace(
    primaryText: String,
    secondaryText: String,
    isPrimary: Boolean,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        DText(
            text = primaryText,
            style = if (isPrimary) Providers.typography.heading4 else Providers.typography.heading6,
            color = if (isPrimary) Providers.color.primary else Providers.color.onSurface,
            maxLines = 3,
        )

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        DText(
            text = secondaryText,
            style = Providers.typography.bodyS,
            color = Providers.color.onSurfaceVariant,
            maxLines = 1,
        )
    }
}