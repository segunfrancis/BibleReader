package com.blazesoftstudio.biblereader.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import biblereader.composeapp.generated.resources.Res
import biblereader.composeapp.generated.resources.ic_chevron_right
import biblereader.composeapp.generated.resources.ic_favourite
import biblereader.composeapp.generated.resources.ic_reader
import biblereader.composeapp.generated.resources.ic_search
import biblereader.composeapp.generated.resources.ic_setting
import biblereader.composeapp.generated.resources.ic_translation
import coil3.compose.AsyncImage
import com.blazesoftstudio.biblereader.designsystem.BibleReaderTheme
import com.blazesoftstudio.biblereader.designsystem.PrimaryText
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Text(
                "Bible Reader",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
            ) {
                AsyncImage(
                    model = "https://images.unsplash.com/photo-1472141521881-95d0e87e2e39",
                    contentDescription = null,
                    modifier = Modifier
                        .height(230.dp)
                        .fillMaxWidth(),
                )
                PrimaryText(
                    text = "\"The Lord is my shepherd: I shall not want.\"",
                    modifier = Modifier.padding(14.dp),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(Res.drawable.ic_reader), contentDescription = null)
                        Spacer(Modifier.width(4.dp))
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            PrimaryText(
                                text = "Reader",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            PrimaryText(
                                text = "Immerse yourself in the Word",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                    Image(
                        painter = painterResource(Res.drawable.ic_chevron_right),
                        contentDescription = null
                    )
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                QuickActionCard(
                    title = "Favourites",
                    icon = Res.drawable.ic_favourite,
                    modifier = Modifier.weight(1f)
                )
                QuickActionCard(
                    title = "Translations",
                    icon = Res.drawable.ic_translation,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                QuickActionCard("Settings", Res.drawable.ic_setting, Modifier.weight(1f))
                QuickActionCard(
                    title = "Archive",
                    icon = Res.drawable.ic_search,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.tertiaryContainer
                )
            }
        }
    }
}

@Composable
private fun QuickActionCard(
    title: String,
    icon: DrawableResource,
    modifier: Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceContainer,
) {
    Card(
        modifier = modifier.height(96.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(painterResource(icon), contentDescription = null, modifier = Modifier.size(22.dp))
            Spacer(Modifier.height(4.dp))
            PrimaryText(
                text = title,
                modifier = Modifier.padding(top = 6.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    BibleReaderTheme {
        HomeScreen()
    }
}
