package com.itis.kmpproj26.feature.words.ui.screen.cards.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DButton
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_add
import kmpproj26native.composeapp.generated.resources.words_empty_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyWords(
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

        DButton(
            onClick = onAddClick,
            text = stringResource(Res.string.common_add),
        )
    }
}
