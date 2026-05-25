package com.itis.kmpproj26.common.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.itis.kmpproj26.common.ui.theme.Providers

@Composable
fun DIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = Providers.componentSize.iconButtonMedium,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(size),
        enabled = enabled
    ) {
        content()
    }
}
