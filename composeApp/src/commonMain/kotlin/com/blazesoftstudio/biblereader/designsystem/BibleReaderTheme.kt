package com.blazesoftstudio.biblereader.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun BibleReaderTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) bibleReaderDarkColorScheme else bibleReaderLightColorScheme,
        typography = BibleReaderTypography,
        shapes = bibleReaderShapes,
        content = content,
    )
}
