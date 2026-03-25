package com.blazesoftstudio.biblereader.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val scriptureFont = FontFamily.Serif // Target: Noto Serif
private val uiFont = FontFamily.SansSerif // Target: Inter

val bibleReaderTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = scriptureFont,
        fontWeight = FontWeight.Normal,
        fontSize = 56.sp,
        lineHeight = 90.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = scriptureFont,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 44.8.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = scriptureFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 25.6.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = uiFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = uiFont,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.sp,
    ),
)
