package com.blazesoftstudio.biblereader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.blazesoftstudio.biblereader.designsystem.BibleReaderFrostedOverlay
import com.blazesoftstudio.biblereader.designsystem.BibleReaderGlowCtaBackground
import com.blazesoftstudio.biblereader.designsystem.BibleReaderInputField
import com.blazesoftstudio.biblereader.designsystem.BibleReaderPrimaryButton
import com.blazesoftstudio.biblereader.designsystem.BibleReaderScriptureRow
import com.blazesoftstudio.biblereader.designsystem.BibleReaderSecondaryButton
import com.blazesoftstudio.biblereader.designsystem.BibleReaderSpacing
import com.blazesoftstudio.biblereader.designsystem.BibleReaderTertiaryButton
import com.blazesoftstudio.biblereader.designsystem.BibleReaderTheme
import com.blazesoftstudio.biblereader.designsystem.BibleReaderVerseOfDayCard

@Composable
@Preview
fun App() {
    BibleReaderTheme {
        var note by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .safeContentPadding()
                .fillMaxSize()
                .padding(horizontal = BibleReaderSpacing.editorialInset, vertical = BibleReaderSpacing.xl),
            verticalArrangement = Arrangement.spacedBy(BibleReaderSpacing.lg),
        ) {
            Text("Psalms", style = MaterialTheme.typography.displayLarge)
            Text("Chapter 23", style = MaterialTheme.typography.headlineMedium)

            BibleReaderVerseOfDayCard(
                title = "Verse of the Day",
                verse = "The Lord is my shepherd; I shall not want.",
                modifier = Modifier.fillMaxWidth(),
            )

            BibleReaderScriptureRow(
                verseNumber = 1,
                scriptureText = "The Lord is my shepherd; I shall not want.",
                isActive = true,
            )
            BibleReaderScriptureRow(
                verseNumber = 2,
                scriptureText = "He maketh me to lie down in green pastures: he leadeth me beside the still waters.",
                isActive = false,
            )

            BibleReaderInputField(
                value = note,
                onValueChange = { note = it },
                label = "Meditation note",
            )

            BibleReaderGlowCtaBackground {
                BibleReaderPrimaryButton(
                    text = "Continue Reading",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            BibleReaderSecondaryButton("Bookmark", onClick = {}, modifier = Modifier.align(Alignment.Start))
            BibleReaderTertiaryButton("Cancel", onClick = {}, modifier = Modifier.align(Alignment.Start))

            Spacer(Modifier.height(BibleReaderSpacing.sm))
            BibleReaderFrostedOverlay {
                Text("Floating Scripture Tools", style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}
