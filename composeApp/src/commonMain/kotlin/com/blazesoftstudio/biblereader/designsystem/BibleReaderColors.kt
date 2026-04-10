package com.blazesoftstudio.biblereader.designsystem

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light scheme (existing tonal sanctuary)
val LightSurface = Color(0xFFFAF9F5)
val LightSurfaceContainerLowest = Color(0xFFFFFFFF)
val LightSurfaceContainerLow = Color(0xFFF4F4EF)
val LightSurfaceContainer = Color(0xFFEDEEE8)
val LightSurfaceContainerHigh = Color(0xFFE7E9E2)
val LightSurfaceContainerHighest = Color(0xFFE0E4DB)
val LightSurfaceDim = Color(0xFFD7DCD2)

val LightPrimary = Color(0xFF58605A)
val LightPrimaryContainer = Color(0xFFDCE5DC)
val LightOnPrimary = Color(0xFFF1FAF1)
val LightOnPrimaryContainer = Color(0xFF1B211D)

val LightSecondaryContainer = Color(0xFFE1E3DF)
val LightOnSecondaryContainer = Color(0xFF4F524F)

val LightTertiaryContainer = Color(0xFFD8FCEA)

val LightOutline = Color(0xFF787C75)
val LightOutlineVariant = Color(0xFFAFB3AC)
val LightOnSurface = Color(0xFF232720)

val bibleReaderLightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,
    tertiaryContainer = LightTertiaryContainer,
    surface = LightSurface,
    surfaceDim = LightSurfaceDim,
    surfaceContainerLowest = LightSurfaceContainerLowest,
    surfaceContainerLow = LightSurfaceContainerLow,
    surfaceContainer = LightSurfaceContainer,
    surfaceContainerHigh = LightSurfaceContainerHigh,
    surfaceContainerHighest = LightSurfaceContainerHighest,
    outline = LightOutline,
    outlineVariant = LightOutlineVariant,
    onSurface = LightOnSurface,
)

// Dark scheme (seed: #4A5D50, neutral variant)
val DarkPrimary = Color(0xFFBECABF)
val DarkSecondary = Color(0xFF757874)
val DarkTertiary = Color(0xFF5E7E70)
val DarkNeutral = Color(0xFF787775)

val DarkSurface = Color(0xFF1A1C1A)
val DarkSurfaceContainerLowest = Color(0xFF151615)
val DarkSurfaceContainerLow = Color(0xFF1F2120)
val DarkSurfaceContainer = Color(0xFF252726)
val DarkSurfaceContainerHigh = Color(0xFF2E3130)
val DarkSurfaceContainerHighest = Color(0xFF373A39)
val DarkSurfaceDim = Color(0xFFD7DCD2)

val DarkOnPrimary = Color(0xFF0F1512)
val DarkOnSecondary = Color(0xFF121412)
val DarkOnTertiary = Color(0xFF0F1613)
val DarkOnSurface = Color(0xFFE2E4E0)
val DarkOutline = Color(0xFFA5A7A3)
val DarkOutlineVariant = Color(0xFF8A8D89)

val bibleReaderDarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkSurfaceContainerHigh,
    onPrimaryContainer = DarkOnSurface,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSurfaceContainer,
    onSecondaryContainer = DarkOnSurface,
    tertiary = DarkTertiary,
    onTertiary = DarkOnTertiary,
    tertiaryContainer = Color(0xFF244236),
    onTertiaryContainer = Color(0xFFD8FCEA),
    surface = DarkSurface,
    surfaceDim = DarkSurfaceDim,
    surfaceContainerLowest = DarkSurfaceContainerLowest,
    surfaceContainerLow = DarkSurfaceContainerLow,
    surfaceContainer = DarkSurfaceContainer,
    surfaceContainerHigh = DarkSurfaceContainerHigh,
    surfaceContainerHighest = DarkSurfaceContainerHighest,
    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,
    onSurface = DarkOnSurface,
)
