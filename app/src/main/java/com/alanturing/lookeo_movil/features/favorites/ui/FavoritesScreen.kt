package com.alanturing.lookeo_movil.features.favorites.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToOutfits: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    Column {
        Text("Mis Favoritos")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToHome) {
            Text("Ir a Home")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToProfile) {
            Text("Ir a mi cuenta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToWardrobe) {
            Text("Ir a mi ropero")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToOutfits) {
            Text("Ir a mis outfits")
        }
    }
}
