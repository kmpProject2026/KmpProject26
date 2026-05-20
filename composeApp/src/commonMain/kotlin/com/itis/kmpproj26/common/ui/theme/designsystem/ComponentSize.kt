package com.itis.kmpproj26.common.ui.theme.designsystem

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ComponentSize(
    val iconSmall: Dp = 16.dp,
    val iconMedium: Dp = 32.dp,
    val iconLarge: Dp = 80.dp,
    val iconExtraLarge: Dp = 160.dp,
    val buttonSmall: Dp = 40.dp,
    val buttonMedium: Dp = 48.dp,
    val buttonLarge: Dp = 56.dp,
    val textFieldMedium: Dp = 60.dp,
)

val LocalComponentSizes = staticCompositionLocalOf { ComponentSize() }
