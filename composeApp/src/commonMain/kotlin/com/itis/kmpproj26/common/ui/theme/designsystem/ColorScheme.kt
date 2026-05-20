package com.itis.kmpproj26.common.ui.theme.designsystem

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val SeedCream = Color(0xFFFFEED6)

private val BackgroundLight = Color(0xFFFFF8EF)
private val SurfaceLight = SeedCream
private val SurfaceContainerLight = Color(0xFFFFF3E4)

private val BackgroundDark = Color(0xFF140C05)
private val SurfaceDark = Color(0xFF1F1308)

private val DeepBrown = Color(0xFF24160A)
private val SurfaceContainerDark = DeepBrown

private val White = Color(0xFFFFFFFF)
private val OffWhite = Color(0xFFFFFFEF)
private val Black = Color(0xFF000000)

private val NeutralContainerLight = Color(0xFFEEDED1)
private val NeutralVariantDark = Color(0xFF52443A)
private val NeutralVariantOnDark = Color(0xFFD2C2B4)

private val PrimaryStrong = Color(0xFFAA6B2D)
private val PrimaryBright = Color(0xFFFFB875)
private val PrimaryContainer = Color(0xFFFFD9B0)

private val TertiaryContainer = Color(0xFFF5E3A6)

private val ErrorBase = Color(0xFFE46962)
private val ErrorContainer = Color(0xFFFFDAD5)

private val OutlineLight = Color(0xFF867567)
private val OutlineDark = Color(0xFF9B8C7F)


private val LightPrimary = PrimaryStrong
private val LightOnPrimary = White
private val LightPrimaryContainer = PrimaryContainer
private val LightOnPrimaryContainer = Color(0xFF3A1B00)

private val LightSecondary = Color(0xFF765848)
private val LightOnSecondary = White
private val LightSecondaryContainer = NeutralContainerLight
private val LightOnSecondaryContainer = Color(0xFF2B1709)

private val LightTertiary = Color(0xFF6B5D2F)
private val LightOnTertiary = White
private val LightTertiaryContainer = TertiaryContainer
private val LightOnTertiaryContainer = Color(0xFF221B00)

private val LightError = ErrorBase
private val LightOnError = OffWhite
private val LightErrorContainer = ErrorContainer
private val LightOnErrorContainer = Color(0xFF410002)

private val LightOnBackground = DeepBrown
private val LightOnSurface = DeepBrown
private val LightSurfaceVariant = NeutralContainerLight
private val LightOnSurfaceVariant = NeutralVariantDark

private val LightOutline = OutlineLight
private val LightOutlineVariant = NeutralVariantOnDark

private val LightInverseOnSurface = Color(0xFFFFF4E8)
private val LightInverseSurface = Color(0xFF3B2A1E)
private val LightInversePrimary = PrimaryBright

private val LightSurfaceTint = LightPrimary
private val LightScrim = Black


private val DarkPrimary = PrimaryBright
private val DarkOnPrimary = Color(0xFF4C2600)
private val DarkPrimaryContainer = Color(0xFF713800)
private val DarkOnPrimaryContainer = PrimaryContainer

private val DarkSecondary = Color(0xFFE5BFA4)
private val DarkOnSecondary = Color(0xFF432A1A)
private val DarkSecondaryContainer = NeutralVariantDark
private val DarkOnSecondaryContainer = Color(0xFFFFDCC4)

private val DarkTertiary = Color(0xFFD9C68D)
private val DarkOnTertiary = Color(0xFF3A3000)
private val DarkTertiaryContainer = Color(0xFF524619)
private val DarkOnTertiaryContainer = TertiaryContainer

private val DarkError = ErrorBase
private val DarkOnError = OffWhite
private val DarkErrorContainer = Color(0xFF8C1D18)
private val DarkOnErrorContainer = ErrorContainer

private val DarkOnBackground = SeedCream
private val DarkOnSurface = SeedCream
private val DarkSurfaceVariant = NeutralVariantDark
private val DarkOnSurfaceVariant = NeutralVariantOnDark

private val DarkOutline = OutlineDark
private val DarkOutlineVariant = NeutralVariantDark

private val DarkInverseOnSurface = SurfaceDark
private val DarkInverseSurface = SeedCream
private val DarkInversePrimary = PrimaryStrong

private val DarkSurfaceTint = DarkPrimary
private val DarkScrim = Black


val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,

    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,

    tertiary = LightTertiary,
    onTertiary = LightOnTertiary,
    tertiaryContainer = LightTertiaryContainer,
    onTertiaryContainer = LightOnTertiaryContainer,

    error = LightError,
    onError = LightOnError,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,

    background = BackgroundLight,
    onBackground = LightOnBackground,

    surface = SurfaceLight,
    onSurface = LightOnSurface,

    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,
    surfaceContainer = SurfaceContainerLight,

    outline = LightOutline,
    outlineVariant = LightOutlineVariant,

    inverseOnSurface = LightInverseOnSurface,
    inverseSurface = LightInverseSurface,
    inversePrimary = LightInversePrimary,

    surfaceTint = LightSurfaceTint,
    scrim = LightScrim,
)

val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,

    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,

    tertiary = DarkTertiary,
    onTertiary = DarkOnTertiary,
    tertiaryContainer = DarkTertiaryContainer,
    onTertiaryContainer = DarkOnTertiaryContainer,

    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = DarkOnErrorContainer,

    background = BackgroundDark,
    onBackground = DarkOnBackground,

    surface = SurfaceDark,
    onSurface = DarkOnSurface,

    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,
    surfaceContainer = SurfaceContainerDark,

    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,

    inverseOnSurface = DarkInverseOnSurface,
    inverseSurface = DarkInverseSurface,
    inversePrimary = DarkInversePrimary,

    surfaceTint = DarkSurfaceTint,
    scrim = DarkScrim,
)

val LocalColorScheme = staticCompositionLocalOf { lightColorScheme() }
