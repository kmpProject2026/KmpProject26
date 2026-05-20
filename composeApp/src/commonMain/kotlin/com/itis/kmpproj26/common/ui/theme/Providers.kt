package com.itis.kmpproj26.common.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.itis.kmpproj26.common.ui.theme.designsystem.ComponentSize
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalColorScheme
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalComponentSizes
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalShape
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalSpacing
import com.itis.kmpproj26.common.ui.theme.designsystem.LocalTypography
import com.itis.kmpproj26.common.ui.theme.designsystem.Shape
import com.itis.kmpproj26.common.ui.theme.designsystem.Spacing
import com.itis.kmpproj26.common.ui.theme.designsystem.Typography

object Providers {
    val spacing: Spacing
        @Composable
        get() = LocalSpacing.current

    val shape: Shape
        @Composable
        get() = LocalShape.current

    val typography: Typography
        @Composable
        get() = LocalTypography.current

    val color: ColorScheme
        @Composable
        get() = LocalColorScheme.current

    val componentSize: ComponentSize
        @Composable
        get() = LocalComponentSizes.current
}
