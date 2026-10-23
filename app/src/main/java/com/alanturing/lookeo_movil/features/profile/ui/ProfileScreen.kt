package com.alanturing.lookeo_movil.features.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit,
) {
    Column {
        Text("Perfil de usuario")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToHome) {
            Text("Volver a Home")
        }
    }
}
