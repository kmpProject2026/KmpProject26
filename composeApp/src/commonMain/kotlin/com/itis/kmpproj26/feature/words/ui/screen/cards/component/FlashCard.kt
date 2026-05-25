package com.itis.kmpproj26.feature.words.ui.screen.cards.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.domain.model.Word

@Composable
fun FlashCard(
    word: Word,
    isShowingTranslation: Boolean,
    onFlip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val rotation by animateFloatAsState(
        targetValue = if (isShowingTranslation) 180f else 0f,
        animationSpec = tween(durationMillis = 260),
    )

    Card(
        modifier = modifier
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density.density
            }
            .clickable(onClick = onFlip),
        shape = Providers.shape.s,
        colors = CardDefaults.cardColors(
            containerColor = Providers.color.surfaceVariant,
        ),
    ) {
        val showBack = rotation > 90f

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Providers.spacing.l)
                .graphicsLayer {
                    if (showBack) {
                        rotationY = 180f
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            if (showBack) {
                FlashCardFace(
                    primaryText = word.translation,
                    secondaryText = word.languagePairTitle,
                    isPrimary = false,
                )
            } else {
                FlashCardFace(
                    primaryText = word.spelling,
                    secondaryText = word.languagePairTitle,
                    isPrimary = true,
                )
            }
        }
    }
}
