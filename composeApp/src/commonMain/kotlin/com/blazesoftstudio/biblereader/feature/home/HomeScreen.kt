package com.blazesoftstudio.biblereader.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Translate
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

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
            Text("Bible Reader", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.SemiBold)
        }
        item {
            Card(shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)) {
                AsyncImage(
                    model = "https://images.unsplash.com/photo-1472141521881-95d0e87e2e39",
                    contentDescription = null,
                    modifier = Modifier
                        .height(230.dp)
                        .fillMaxWidth(),
                )
                Text(
                    "\"The Lord is my shepherd: I shall not want.\"",
                    modifier = Modifier.padding(14.dp),
                    style = MaterialTheme.typography.displaySmall,
                    fontStyle = FontStyle.Italic,
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
                        Icon(Icons.Outlined.MenuBook, contentDescription = null)
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            Text("Reader", fontWeight = FontWeight.Bold)
                            Text("Immerse yourself in the Word", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                    Text("→")
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                QuickActionCard("Favorites", Icons.Outlined.BookmarkBorder, Modifier.weight(1f))
                QuickActionCard("Translations", Icons.Outlined.Translate, Modifier.weight(1f))
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                QuickActionCard("Settings", Icons.Outlined.Settings, Modifier.weight(1f))
                QuickActionCard("Archive", Icons.Outlined.MenuBook, Modifier.weight(1f), MaterialTheme.colorScheme.tertiaryContainer)
            }
        }
    }
}

@Composable
private fun QuickActionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceContainer,
) {
    Card(modifier = modifier.height(96.dp), colors = CardDefaults.cardColors(containerColor = color)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(22.dp))
            Text(title, modifier = Modifier.padding(top = 6.dp), fontWeight = FontWeight.SemiBold)
        }
    }
}
