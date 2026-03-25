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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.blazesoftstudio.biblereader.designsystem.BibleReaderPrimaryButton

@Composable
fun OnboardingScreen(
    onGetStarted: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
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
                        colors = listOf(Color(0xCCF7F2E7), Color(0xE5F0ECE2)),
                    ),
                ),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Text("Bible Reader", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.SemiBold)
                Text(
                    "Sacred Wisdom in your hands.",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    "Experience the word through a lens of serenity and modern elegance.",
                    style = MaterialTheme.typography.bodyLarge,
                )

                OnboardingFeatureCard("Offline Sanctuary", "Read anywhere, without interruption.", Icons.Outlined.CloudOff)
                OnboardingFeatureCard("Daily Verses", "Curated wisdom for your morning.", Icons.Outlined.AutoStories)
                OnboardingFeatureCard("Personal Favourites", "Preserve verses that speak to you", Icons.Outlined.BookmarkBorder)
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
    icon: androidx.compose.ui.graphics.vector.ImageVector,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.6f)),
    ) {
        androidx.compose.material3.ListItem(
            headlineContent = { Text(title, fontWeight = FontWeight.SemiBold) },
            supportingContent = { Text(subtitle) },
            leadingContent = { androidx.compose.material3.Icon(icon, contentDescription = null) },
        )
    }
}
