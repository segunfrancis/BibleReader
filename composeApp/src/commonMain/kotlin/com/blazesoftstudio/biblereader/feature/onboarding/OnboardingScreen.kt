package com.blazesoftstudio.biblereader.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import biblereader.composeapp.generated.resources.Res
import biblereader.composeapp.generated.resources.ic_daily_verses
import biblereader.composeapp.generated.resources.ic_offline_sanctuary
import biblereader.composeapp.generated.resources.ic_personal_favourite
import coil3.compose.AsyncImage
import com.blazesoftstudio.biblereader.designsystem.BibleReaderPrimaryButton
import com.blazesoftstudio.biblereader.designsystem.BibleReaderTheme
import com.blazesoftstudio.biblereader.designsystem.PrimaryText
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardingScreen(
    onGetStarted: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://images.unsplash.com/photo-1504052434569-70ad5836ab65",
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.82f),
                            MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.9f),
                        ),
                    ),
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                PrimaryText(
                    text = "Sacred Wisdom in your hands.",
                    style = MaterialTheme.typography.displayMedium.copy(textAlign = TextAlign.Center)
                )
                PrimaryText(
                    text = "Experience the word through a lens of serenity and modern elegance.",
                    style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center)
                )

                OnboardingFeatureCard(
                    title = "Offline Sanctuary",
                    subtitle = "Read anywhere, without interruption.",
                    icon = Res.drawable.ic_offline_sanctuary
                )
                OnboardingFeatureCard(
                    title = "Daily Verses",
                    subtitle = "Curated wisdom for your morning.",
                    icon = Res.drawable.ic_daily_verses
                )
                OnboardingFeatureCard(
                    title = "Personal Favourites",
                    subtitle = "Preserve verses that speak to you",
                    icon = Res.drawable.ic_personal_favourite
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            BibleReaderPrimaryButton(
                text = "Get Started",
                onClick = onGetStarted,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun OnboardingFeatureCard(
    title: String,
    subtitle: String,
    icon: DrawableResource,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest.copy(alpha = 0.72f),
        ),
    ) {
        androidx.compose.material3.ListItem(
            headlineContent = {
                PrimaryText(
                    text = title,
                    style = MaterialTheme.typography.labelLarge
                )
            },
            supportingContent = {
                PrimaryText(
                    text = subtitle,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            leadingContent = { Icon(painterResource(icon), contentDescription = null) },
        )
    }
}


@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnboardingPreview() {
    BibleReaderTheme {
        OnboardingScreen(onGetStarted = {})
    }
}
