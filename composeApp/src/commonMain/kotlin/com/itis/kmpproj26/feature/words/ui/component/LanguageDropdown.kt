package com.itis.kmpproj26.feature.words.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.domain.model.WordLanguage

@Composable
fun LanguageDirectionPicker(
    sourceTitle: String,
    targetTitle: String,
    selectedSourceLanguage: WordLanguage,
    selectedTargetLanguage: WordLanguage,
    sourceLanguages: List<WordLanguage>,
    targetLanguages: List<WordLanguage>,
    onSourceLanguageSelected: (WordLanguage) -> Unit,
    onTargetLanguageSelected: (WordLanguage) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Providers.spacing.s),
    ) {
        LanguageDropdown(
            title = sourceTitle,
            selectedLanguage = selectedSourceLanguage,
            languages = sourceLanguages,
            onLanguageSelected = onSourceLanguageSelected,
            modifier = Modifier.weight(1f),
        )

        LanguageDropdown(
            title = targetTitle,
            selectedLanguage = selectedTargetLanguage,
            languages = targetLanguages,
            onLanguageSelected = onTargetLanguageSelected,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun LanguageDropdown(
    title: String,
    selectedLanguage: WordLanguage,
    languages: List<WordLanguage>,
    onLanguageSelected: (WordLanguage) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        DText(
            text = title,
            style = Providers.typography.bodyS,
            color = Providers.color.onSurfaceVariant,
            maxLines = 1,
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth(),
            ) {
                DText(
                    text = selectedLanguage.title,
                    style = Providers.typography.bodyM,
                    maxLines = 1,
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        text = {
                            DText(
                                text = language.title,
                                maxLines = 1,
                            )
                        },
                        onClick = {
                            expanded = false
                            onLanguageSelected(language)
                        },
                    )
                }
            }
        }
    }
}
