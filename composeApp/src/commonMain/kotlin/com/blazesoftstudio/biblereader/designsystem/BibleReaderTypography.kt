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

@Composable
fun bibleReaderTypography(): Typography {
    val scriptureFont = FontFamily(Font(Res.font.NotoSerif)) // Target: Noto Serif
    val uiFont = FontFamily(Font(Res.font.Inter)) // Target: Inter

    return Typography(
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
}
