package com.blazesoftstudio.biblereader.designsystem

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import biblereader.composeapp.generated.resources.Inter
import biblereader.composeapp.generated.resources.NotoSerif
import biblereader.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

val scriptureFont @Composable get() = FontFamily(Font(Res.font.NotoSerif)) // Target: Noto Serif
val uiFont @Composable get() = FontFamily(Font(Res.font.Inter)) // Target: Inter

val BibleReaderTypography: Typography
    @Composable get() = Typography(
        displayLarge = TextStyle(
            fontFamily = scriptureFont,
            fontWeight = FontWeight.Normal,
            fontSize = 56.sp,
            lineHeight = 90.sp,
        ),
        displayMedium = TextStyle(
            fontFamily = scriptureFont,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            lineHeight = 60.sp
        ),
        displaySmall = TextStyle(
            fontFamily = scriptureFont,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 45.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = scriptureFont,
            fontWeight = FontWeight.Medium,
            fontSize = 28.sp,
            lineHeight = 44.8.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = scriptureFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = scriptureFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 22.8.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = scriptureFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 25.6.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = scriptureFont,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 28.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = uiFont,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = uiFont,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 14.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = uiFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    )
