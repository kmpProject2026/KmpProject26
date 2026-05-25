package com.itis.kmpproj26.feature.words.ui.screen.cards

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.kmpproj26.common.ui.component.DDialog
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.domain.model.Word
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.ui.component.LanguageDirectionPicker
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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
import kotlin.math.min

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

@Composable
private fun SwipeGuide(
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
            marker = "←",
            title = stringResource(Res.string.words_repeat),
            modifier = Modifier.weight(1f),
        )

        SwipeGuideItem(
            action = CardSwipeAction.Remember,
            activeAction = activeAction,
            marker = "→",
            title = stringResource(Res.string.words_remember),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun SwipeGuideItem(
    action: CardSwipeAction,
    activeAction: CardSwipeAction?,
    marker: String,
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
            text = marker,
            style = Providers.typography.bodyM,
            color = contentColor,
            maxLines = 1,
        )

        Spacer(modifier = Modifier.width(Providers.spacing.xs))

        DText(
            text = title,
            style = Providers.typography.bodyM,
            color = contentColor,
            maxLines = 1,
        )
    }
}

@Composable
private fun SwipeableCardDeck(
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

@Composable
private fun FlashCard(
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

@Composable
private fun FlashCardFace(
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

private fun visibleCardStack(
    words: List<Word>,
    currentIndex: Int,
): List<Pair<Int, Word>> {
    if (words.isEmpty()) return emptyList()

    return (0 until min(3, words.size)).map { position ->
        val index = (currentIndex + position) % words.size
        position to words[index]
    }
}

private enum class CardSwipeAction {
    Repeat,
    Remember,
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
