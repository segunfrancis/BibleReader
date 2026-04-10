package com.blazesoftstudio.biblereader.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import biblereader.composeapp.generated.resources.Res
import biblereader.composeapp.generated.resources.il_sanctuary_atmosphere
import com.blazesoftstudio.biblereader.core.data.BibleTranslation
import com.blazesoftstudio.biblereader.designsystem.BibleReaderPrimaryButton
import com.blazesoftstudio.biblereader.designsystem.BibleReaderTheme
import com.blazesoftstudio.biblereader.designsystem.PrimaryText
import com.blazesoftstudio.biblereader.feature.onboarding.model.OnboardingUiState
import org.jetbrains.compose.resources.painterResource

@Composable
fun TranslationSelectionScreen(
    uiState: OnboardingUiState,
    onTranslationToggle: (String) -> Unit,
    onDownloadAndContinue: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
            val gradientBrush = Brush.verticalGradient(
                colors = listOf(Color.White, Color.LightGray, Color.Transparent)
            )
            Box(
                modifier = Modifier.weight(0.32F).fillMaxWidth()
                    .background(brush = gradientBrush),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(Res.drawable.il_sanctuary_atmosphere),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.FillBounds
                )
                Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                    PrimaryText(
                        text = "Choose your Translation",
                        style = MaterialTheme.typography.displaySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    PrimaryText(
                        text = "Download at least one version to begin your journey",
                        style = MaterialTheme.typography.bodyLarge.copy(fontStyle = FontStyle.Italic)
                    )
                }
            }
            if (uiState.loadingTranslations) {
                Box(
                    modifier = Modifier.weight(0.68f).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(0.72f),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(uiState.translations, key = { it.id }) { translation ->
                        TranslationCard(
                            translation = translation,
                            isSelected = translation.id in uiState.selectedIds,
                            onClick = { onTranslationToggle(translation.id) },
                        )
                    }
                    item {
                        PrimaryText(
                            text = "Additional translations and languages can be downloaded later in settings. Your choices help us tailor your initial reading plan and study guides.",
                            style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }

            BibleReaderPrimaryButton(
                text = if (uiState.downloadingTranslations) "Downloading..." else "Download & Continue",
                onClick = {
                    if (uiState.selectedIds.isNotEmpty() && !uiState.downloadingTranslations) onDownloadAndContinue()
                },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
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
            .clip(CardDefaults.shape)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                PrimaryText(text = translation.language.uppercase(), style = MaterialTheme.typography.labelSmall)
                RadioButton(
                    selected = isSelected,
                    onClick = {},
                    enabled = false,
                    modifier = Modifier.padding(5.dp)
                )
            }
            PrimaryText(
                text = translation.shortName,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            PrimaryText(text = translation.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
@Preview(name = "light", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
fun TranslationSelectionPreview() {
    BibleReaderTheme {
        TranslationSelectionScreen(
            uiState = OnboardingUiState(),
            onTranslationToggle = {},
            onDownloadAndContinue = {}
        )
    }
}
