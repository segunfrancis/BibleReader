package com.blazesoftstudio.biblereader.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun BibleReaderPrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.clip(fullPillShape),
        shape = fullPillShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        contentPadding = PaddingValues(horizontal = BibleReaderSpacing.xl, vertical = BibleReaderSpacing.md),
    ) {
        Text(text = text)
    }
}

@Composable
fun BibleReaderGlowCtaBackground(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .clip(fullPillShape)
            .background(
                brush = Brush.linearGradient(
                    listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primaryContainer),
                ),
            )
            .padding(BibleReaderSpacing.xs),
    ) {
        content()
    }
}

@Composable
fun BibleReaderSecondaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = fullPillShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
    ) { Text(text = text) }
}

@Composable
fun BibleReaderTertiaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary),
    ) { Text(text = text) }
}

@Composable
fun BibleReaderVerseOfDayCard(title: String, verse: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = verseCardShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    ) {
        Column(
            modifier = Modifier.padding(BibleReaderSpacing.xl),
            verticalArrangement = Arrangement.spacedBy(BibleReaderSpacing.sm),
        ) {
            Text(title, style = MaterialTheme.typography.labelMedium)
            Text(verse, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun BibleReaderInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        shape = MaterialTheme.shapes.small,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
        ),
    )
}

@Composable
fun BibleReaderFrostedOverlay(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.72f))
            .padding(horizontal = BibleReaderSpacing.lg, vertical = BibleReaderSpacing.sm),
    ) {
        content()
    }
}

@Composable
fun BibleReaderScriptureRow(
    verseNumber: Int,
    scriptureText: String,
    isActive: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(verseHighlightShape)
            .background(if (isActive) MaterialTheme.colorScheme.tertiaryContainer else Color.Transparent)
            .padding(horizontal = BibleReaderSpacing.xs, vertical = BibleReaderSpacing.sm),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = verseNumber.toString(),
            modifier = Modifier
                .width(BibleReaderSpacing.xl)
                .padding(end = 7.dp),
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.outline,
                fontFamily = FontFamily.SansSerif,
            ),
        )
        Text(
            text = scriptureText,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun BibleReaderAmbientFabShadow(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .blur(24.dp)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.06f), shape = CircleShape)
            .alpha(0.8f),
    )
}
