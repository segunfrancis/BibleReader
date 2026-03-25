package com.blazesoftstudio.biblereader.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun BibleReaderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) bibleReaderDarkColorScheme else bibleReaderLightColorScheme,
        typography = bibleReaderTypography(),
        shapes = bibleReaderShapes,
        content = content,
    )
}
