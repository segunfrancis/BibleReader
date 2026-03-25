package com.blazesoftstudio.biblereader.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.blazesoftstudio.biblereader.core.data.BibleTranslation
import com.blazesoftstudio.biblereader.designsystem.BibleReaderPrimaryButton
import com.blazesoftstudio.biblereader.feature.onboarding.model.OnboardingUiState

@Composable
fun TranslationSelectionScreen(
    uiState: OnboardingUiState,
    onTranslationToggle: (String) -> Unit,
    onDownloadAndContinue: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = "https://images.unsplash.com/photo-1579546929518-9e396f3cc809",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.28f),
            )
            if (uiState.loadingTranslations) {
                Box(modifier = Modifier.weight(0.72f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(0.72f),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    item {
                        Text("Choose your Translation", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
                        Text("Download at least one version to begin your journey", style = MaterialTheme.typography.bodyLarge)
                    }
                    items(uiState.translations, key = { it.id }) { translation ->
                        TranslationCard(
                            translation = translation,
                            isSelected = translation.id in uiState.selectedIds,
                            onClick = { onTranslationToggle(translation.id) },
                        )
                    }
                    item {
                        Text(
                            "Additional translations and languages can be downloaded later in settings.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                    }
                }
            }
        }

        BibleReaderPrimaryButton(
            text = if (uiState.downloadingTranslations) "Downloading..." else "Download & Continue",
            onClick = {
                if (uiState.selectedIds.isNotEmpty() && !uiState.downloadingTranslations) onDownloadAndContinue()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .fillMaxWidth(),
        )
    }
}

@Composable
private fun TranslationCard(
    translation: BibleTranslation,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F4F1)),
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(translation.language.uppercase(), style = MaterialTheme.typography.labelSmall)
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (isSelected) Color(0xFF5E6761) else Color.Transparent)
                        .padding(5.dp),
                ) {
                    Text(if (isSelected) "✓" else "○", color = if (isSelected) Color.White else Color(0xFF5E6761))
                }
            }
            Text(translation.abbreviation, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(translation.name, style = MaterialTheme.typography.bodyLarge)
            Text("⬇ ${translation.sizeInMb}MB", style = MaterialTheme.typography.labelMedium)
        }
    }
}
