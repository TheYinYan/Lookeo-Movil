package com.alanturing.lookeo_movil.features.outfits.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun OutfitsScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    Column {
        Text("Mis outfits")

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

        Button(onClick = onNavigateToFavorites) {
            Text("Ir a mis favoritos")
        }

    }
}
