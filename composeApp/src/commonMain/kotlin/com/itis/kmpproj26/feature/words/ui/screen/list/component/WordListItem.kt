package com.itis.kmpproj26.feature.words.ui.screen.list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DIconButton
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.words.domain.model.Word
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_close
import kmpproj26native.composeapp.generated.resources.ic_close
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.fav.yogadaily.ui.component.DIcon

@Composable
fun WordListItem(
    word: Word,
    onDeleteClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = Providers.shape.s,
        colors = CardDefaults.cardColors(
            containerColor = Providers.color.surfaceVariant,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Providers.spacing.m),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                DText(
                    text = word.spelling,
                    style = Providers.typography.titleMedium,
                )

                Spacer(modifier = Modifier.height(Providers.spacing.xxs))

                DText(
                    text = word.translation,
                    style = Providers.typography.bodyM,
                    color = Providers.color.onSurfaceVariant,
                )

                Spacer(modifier = Modifier.height(Providers.spacing.xxs))

                DText(
                    text = word.languagePairTitle,
                    style = Providers.typography.bodyS,
                    color = Providers.color.onSurfaceVariant,
                )
            }

            DIconButton(onClick = onDeleteClick) {
                DIcon(
                    painter = painterResource(Res.drawable.ic_close),
                    contentDescription = stringResource(Res.string.common_close),
                    tint = Providers.color.onSurfaceVariant,
                )
            }
        }
    }
}