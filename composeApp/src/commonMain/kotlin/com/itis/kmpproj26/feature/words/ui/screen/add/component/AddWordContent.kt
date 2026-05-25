package com.itis.kmpproj26.feature.words.ui.screen.add.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.itis.kmpproj26.common.ui.component.DButton
import com.itis.kmpproj26.common.ui.component.DIconButton
import com.itis.kmpproj26.common.ui.component.DOutlinedInputSection
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage
import com.itis.kmpproj26.feature.words.ui.component.LanguageDirectionPicker
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordState
import com.itis.kmpproj26.feature.words.ui.screen.add.util.toStringRes
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_back
import kmpproj26native.composeapp.generated.resources.common_done
import kmpproj26native.composeapp.generated.resources.ic_back
import kmpproj26native.composeapp.generated.resources.words_add_title
import kmpproj26native.composeapp.generated.resources.words_enter_translation_label
import kmpproj26native.composeapp.generated.resources.words_enter_word_label
import kmpproj26native.composeapp.generated.resources.words_source_language
import kmpproj26native.composeapp.generated.resources.words_spelling
import kmpproj26native.composeapp.generated.resources.words_target_language
import kmpproj26native.composeapp.generated.resources.words_translate
import kmpproj26native.composeapp.generated.resources.words_translation
import kmpproj26native.composeapp.generated.resources.words_yandex_powered
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.fav.yogadaily.ui.component.DIcon

private const val YANDEX_DICTIONARY_URL = "http://api.yandex.com/dictionary"

@Composable
fun AddWordContent(
    state: AddWordState,
    onSpellingChanged: (String) -> Unit,
    onTranslationChanged: (String) -> Unit,
    onSourceLanguageSelected: (WordLanguage) -> Unit,
    onTargetLanguageSelected: (WordLanguage) -> Unit,
    onTranslateClick: () -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
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
                text = stringResource(Res.string.words_add_title),
                modifier = Modifier.weight(1f),
                style = Providers.typography.heading6,
            )
        }

        Spacer(modifier = Modifier.height(Providers.spacing.l))

        LanguageDirectionPicker(
            sourceTitle = stringResource(Res.string.words_source_language),
            targetTitle = stringResource(Res.string.words_target_language),
            selectedSourceLanguage = state.selectedSourceLanguage,
            selectedTargetLanguage = state.selectedTargetLanguage,
            sourceLanguages = state.availableSourceLanguages,
            targetLanguages = state.availableTargetLanguages,
            onSourceLanguageSelected = onSourceLanguageSelected,
            onTargetLanguageSelected = onTargetLanguageSelected,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        DOutlinedInputSection(
            title = stringResource(Res.string.words_spelling),
            value = state.spelling,
            onValueChange = onSpellingChanged,
            placeholder = stringResource(Res.string.words_enter_word_label),
            isError = state.spellingError != null,
            errorText = state.spellingError?.toStringRes(isTranslation = false),
        )

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        DButton(
            text = stringResource(Res.string.words_translate),
            onClick = onTranslateClick,
            enabled = !state.isTranslating && !state.isSaving,
        )

        Spacer(modifier = Modifier.height(Providers.spacing.xs))

        DText(
            text = stringResource(Res.string.words_yandex_powered),
            modifier = Modifier.clickable {
                uriHandler.openUri(YANDEX_DICTIONARY_URL)
            },
            style = Providers.typography.bodyS,
            color = Providers.color.primary,
        )

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        DOutlinedInputSection(
            title = stringResource(Res.string.words_translation),
            value = state.translation,
            onValueChange = onTranslationChanged,
            placeholder = stringResource(Res.string.words_enter_translation_label),
            isError = state.translationError != null,
            errorText = state.translationError?.toStringRes(isTranslation = true),
            singleLine = false,
        )

        Spacer(modifier = Modifier.height(Providers.spacing.xl))

        DButton(
            text = stringResource(Res.string.common_done),
            onClick = onSaveClick,
            enabled = !state.isTranslating && !state.isSaving,
        )
    }
}