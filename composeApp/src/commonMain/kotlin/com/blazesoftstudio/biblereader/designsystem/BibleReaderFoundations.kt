package com.blazesoftstudio.biblereader.designsystem

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

object BibleReaderSpacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 48.dp
    val editorialInset = 64.dp
}

val bibleReaderShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
)

val verseHighlightShape = RoundedCornerShape(8.dp)
val verseCardShape = RoundedCornerShape(16.dp)
val fullPillShape = RoundedCornerShape(percent = 50)
