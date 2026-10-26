package com.alanturing.lookeo_movil.features.wardrobe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WardrobeScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToOutfits: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Column {
        Text("¡Bienvenido a Armario!")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToHome) {
            Text("Ir a Home")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToOutfits) {
            Text("Ir a mis outfits")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToFavorites) {
            Text("Ir a mis favoritos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToProfile) {
            Text("Ir a mi cuenta")
        }
    }
}
