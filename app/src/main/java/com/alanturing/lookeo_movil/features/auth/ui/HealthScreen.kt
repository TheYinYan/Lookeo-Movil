package com.alanturing.lookeo_movil.features.auth.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HealthScreen(
    viewModel: HealthViewModel = hiltViewModel()
) {
    val message by viewModel.message.collectAsStateWithLifecycle()

    Text(
        text = message
    )

    Button(
        onClick = { viewModel.checkConnection() }
    ) {
        Text("Comprobar conexión")
    }
}