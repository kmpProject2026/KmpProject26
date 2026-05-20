package com.itis.kmpproj26.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.itis.kmpproj26.common.ui.theme.designsystem.ComponentSize
import com.itis.kmpproj26.common.ui.theme.designsystem.DarkColorScheme
import com.itis.kmpproj26.common.ui.theme.designsystem.LightColorScheme
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalColorScheme
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalComponentSizes
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalShape
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalSpacing
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalTypography
import com.itis.kmpproj26.common.ui.theme.designsystem.Shape
import com.itis.kmpproj26.common.ui.theme.designsystem.Spacing
import com.itis.kmpproj26.common.ui.theme.designsystem.Typography


@Composable
fun DTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalColorScheme provides colors,
        LocalTypography provides Typography(),
        LocalSpacing provides Spacing(),
        LocalShape provides Shape(),
        LocalComponentSizes provides ComponentSize()
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = MaterialTheme.typography,
            content = content
        )
    }
}
