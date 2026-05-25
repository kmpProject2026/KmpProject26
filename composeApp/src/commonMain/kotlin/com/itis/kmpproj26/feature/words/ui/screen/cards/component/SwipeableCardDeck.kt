package com.itis.kmpproj26.feature.words.ui.screen.cards.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.itis.kmpproj26.feature.words.domain.model.Word
import com.itis.kmpproj26.feature.words.ui.screen.cards.util.CardSwipeAction
import com.itis.kmpproj26.feature.words.ui.screen.cards.util.visibleCardStack
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun SwipeableCardDeck(
    words: List<Word>,
    currentIndex: Int,
    onRepeatSwipe: () -> Unit,
    onRememberSwipe: () -> Unit,
    onActiveSwipeActionChanged: (CardSwipeAction?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val stack = remember(words, currentIndex) {
        visibleCardStack(words = words, currentIndex = currentIndex)
    }
    val currentWord = stack.firstOrNull()?.second ?: return
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current
    val swipeThresholdPx = with(density) { 92.dp.toPx() }
    val highlightThresholdPx = with(density) { 36.dp.toPx() }
    var dragX by remember { mutableStateOf(0f) }
    var dragY by remember { mutableStateOf(0f) }
    var isShowingTranslation by remember { mutableStateOf(false) }
    var isFlyingOut by remember { mutableStateOf(false) }
    val activeAction = when {
        dragX <= -highlightThresholdPx -> CardSwipeAction.Repeat
        dragX >= highlightThresholdPx -> CardSwipeAction.Remember
        else -> null
    }

    suspend fun animateDragTo(
        targetX: Float,
        targetY: Float,
        animationSpec: AnimationSpec<Float>,
    ) {
        val x = Animatable(dragX)
        val y = Animatable(dragY)

        coroutineScope {
            launch {
                x.animateTo(targetX, animationSpec) {
                    dragX = value
                }
            }
            launch {
                y.animateTo(targetY, animationSpec) {
                    dragY = value
                }
            }
        }
    }

    LaunchedEffect(activeAction) {
        onActiveSwipeActionChanged(activeAction)
    }

    LaunchedEffect(currentWord.id, words.size, currentIndex) {
        dragX = 0f
        dragY = 0f
        isShowingTranslation = false
        isFlyingOut = false
        onActiveSwipeActionChanged(null)
    }

    DisposableEffect(Unit) {
        onDispose { onActiveSwipeActionChanged(null) }
    }

    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val flyOutDistance = with(density) { maxWidth.toPx() } * 1.4f

        stack.asReversed().forEach { (position, word) ->
            val isTopCard = position == 0
            val scale = 1f - position * 0.045f
            val verticalOffset = with(density) { (position * 14).dp.toPx() }
            val horizontalPadding = (position * 8).dp
            val cardModifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding)
                .aspectRatio(1.18f)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationY = verticalOffset
                    alpha = 1f - position * 0.14f
                }

            if (isTopCard) {
                FlashCard(
                    word = word,
                    isShowingTranslation = isShowingTranslation,
                    onFlip = {
                        if (!isFlyingOut) {
                            isShowingTranslation = !isShowingTranslation
                        }
                    },
                    modifier = cardModifier
                        .graphicsLayer {
                            translationX = dragX
                            translationY = dragY
                            rotationZ = dragX / 32f
                        }
                        .pointerInput(currentWord.id, isFlyingOut) {
                            detectDragGestures(
                                onDrag = { change, dragAmount ->
                                    if (!isFlyingOut) {
                                        change.consume()
                                        dragX += dragAmount.x
                                        dragY += dragAmount.y
                                    }
                                },
                                onDragCancel = {
                                    if (!isFlyingOut) {
                                        coroutineScope.launch {
                                            animateDragTo(
                                                targetX = 0f,
                                                targetY = 0f,
                                                animationSpec = spring(
                                                    stiffness = Spring.StiffnessMediumLow,
                                                ),
                                            )
                                        }
                                    }
                                },
                                onDragEnd = {
                                    if (!isFlyingOut) {
                                        val action = when {
                                            dragX <= -swipeThresholdPx -> CardSwipeAction.Repeat
                                            dragX >= swipeThresholdPx -> CardSwipeAction.Remember
                                            else -> null
                                        }

                                        coroutineScope.launch {
                                            if (action == null) {
                                                animateDragTo(
                                                    targetX = 0f,
                                                    targetY = 0f,
                                                    animationSpec = spring(
                                                        stiffness = Spring.StiffnessMediumLow,
                                                    ),
                                                )
                                            } else {
                                                isFlyingOut = true
                                                val targetX = when (action) {
                                                    CardSwipeAction.Repeat -> -flyOutDistance
                                                    CardSwipeAction.Remember -> flyOutDistance
                                                }

                                                animateDragTo(
                                                    targetX = targetX,
                                                    targetY = dragY * 0.4f,
                                                    animationSpec = tween(durationMillis = 220),
                                                )

                                                when (action) {
                                                    CardSwipeAction.Repeat -> onRepeatSwipe()
                                                    CardSwipeAction.Remember -> onRememberSwipe()
                                                }

                                                dragX = 0f
                                                dragY = 0f
                                                isShowingTranslation = false
                                                isFlyingOut = false
                                                onActiveSwipeActionChanged(null)
                                            }
                                        }
                                    }
                                },
                            )
                        },
                )
            } else {
                FlashCard(
                    word = word,
                    isShowingTranslation = false,
                    onFlip = {},
                    modifier = cardModifier,
                )
            }
        }
    }
}
