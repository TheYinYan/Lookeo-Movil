package com.alanturing.lookeo_movil.features.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
) {
    Column {
        Text("¡Bienvenido a Lookeo!")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToProfile) {
            Text("Ir a mi cuenta")
        }
    }
}
