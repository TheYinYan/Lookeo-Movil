package com.alanturing.lookeo_movil.features.auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
) {
    val viewModel: LoginViewModel = viewModel()

    Column(
        modifier = Modifier
    ) {
        val state by viewModel.state.collectAsStateWithLifecycle()
        val date by viewModel.date.collectAsStateWithLifecycle()

        Text("Introduce tu usuario y contraseña")

        Spacer(modifier = Modifier.height(16.dp))

        val textFieldsModifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
        val customQuantityKeyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Decimal
        )

        TextField(
            modifier = textFieldsModifier,
            value = date.userName,
            keyboardOptions = customQuantityKeyboardOptions,
            onValueChange = {
                    viewModel.updateGuest(it)
            },
        )

        Button(onClick = onNavigateToHome) {
            Text("Iniciar sesión")
        }
    }
}
