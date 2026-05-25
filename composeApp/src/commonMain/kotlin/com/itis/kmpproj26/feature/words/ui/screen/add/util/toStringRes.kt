package com.itis.kmpproj26.feature.words.ui.screen.add.util

import androidx.compose.runtime.Composable
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordInputError
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.words_error_empty_translation
import kmpproj26native.composeapp.generated.resources.words_error_empty_word
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddWordInputError.toStringRes(
    isTranslation: Boolean,
): String {
    return when (this) {
        AddWordInputError.EMPTY -> stringResource(
            if (isTranslation) {
                Res.string.words_error_empty_translation
            } else {
                Res.string.words_error_empty_word
            }
        )
    }
}
