package com.itis.kmpproj26.common.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.itis.kmpproj26.common.ui.theme.Providers

@Composable
fun DText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = Providers.typography.bodyL,
    color: Color = Providers.color.onSurface,
    maxLines: Int = Int.MAX_VALUE,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = Providers.spacing.none,
        vertical = Providers.spacing.none
    ),
) {
    Text(
        text = text,
        style = style,
        modifier = modifier.padding(contentPadding),
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
    )
}
