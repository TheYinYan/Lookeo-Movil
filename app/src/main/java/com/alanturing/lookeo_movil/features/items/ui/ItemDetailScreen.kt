package com.alanturing.lookeo_movil.features.items.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alanturing.lookeo_movil.core.ui.components.LookeoTopBar
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoDarkSurface
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite

@Composable
fun ItemDetailScreen(
	onNavigateToWardrobe: () -> Unit,
	viewModel: ItemDetailViewModel = hiltViewModel(),
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	LaunchedEffect(uiState.isSuccess) {
		if (uiState.isSuccess) {
			viewModel.resetSuccessState()
			onNavigateToWardrobe()
		}
	}

	Scaffold(
		topBar = {
			LookeoTopBar(onNavigateToWardrobe)
		},
	) { innerPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(LookeoBlack)
				.padding(innerPadding)
				.padding(16.dp),
		) {
			Text(
				text = "Editar prenda",
				color = LookeoWhite,
				style = MaterialTheme.typography.displaySmall,
				modifier = Modifier.padding(bottom = 12.dp),
			)
			Text(
				text = "Ajusta las etiquetas y guarda los cambios.",
				color = LookeoGray,
				style = MaterialTheme.typography.labelMedium,
				modifier = Modifier.padding(bottom = 12.dp),
			)

			if (!uiState.isLoaded && uiState.errorMessage == null) {
				CircularProgressIndicator(color = LookeoGold)
				return@Column
			}

			Spacer(modifier = Modifier.height(16.dp))

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(12.dp),
			) {
				DropdownField(
					label = "Tipo",
					selectedOption = uiState.tipo.ifBlank { "Selecciona" },
					options = listOf("Camisa", "Pantalón", "Zapatos", "Abrigo"),
					onOptionSelected = { viewModel.onTipoChanged(it) },
					modifier = Modifier.weight(1f),
				)
				DropdownField(
					label = "Color",
					selectedOption = uiState.color.ifBlank { "Selecciona" },
					options = listOf("Blanco", "Negro", "Azul", "Rojo"),
					onOptionSelected = { viewModel.onColorChanged(it) },
					modifier = Modifier.weight(1f),
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(12.dp),
			) {
				DropdownField(
				label = "Temporada",
				selectedOption = uiState.temporada.ifBlank { "Selecciona" },
				options = listOf("Primavera", "Verano", "Otoño", "Invierno"),
				onOptionSelected = { viewModel.onTemporadaChanged(it) },
				modifier = Modifier.weight(1f),
				)
				DropdownField(
					label = "Ocasión",
					selectedOption = uiState.ocasion.ifBlank { "Selecciona" },
					options = listOf("Casual", "Formal", "Deportivo"),
					onOptionSelected = { viewModel.onOcasionChanged(it) },
					modifier = Modifier.weight(1f),
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			OutlinedTextField(
				value = uiState.notas,
				onValueChange = { viewModel.onNotasChanged(it) },
				placeholder = { Text("Notas adicionales...", color = LookeoGray, fontSize = 12.sp) },
				modifier = Modifier
					.fillMaxWidth()
					.height(100.dp),
				colors = OutlinedTextFieldDefaults.colors(
					focusedContainerColor = LookeoDarkSurface,
					unfocusedContainerColor = LookeoDarkSurface,
					focusedBorderColor = Color.Transparent,
					unfocusedBorderColor = Color.Transparent,
					focusedTextColor = LookeoWhite,
					unfocusedTextColor = LookeoWhite,
				),
				shape = RoundedCornerShape(12.dp),
				textStyle = TextStyle(fontSize = 12.sp)
			)

			uiState.errorMessage?.let { error ->
				Text(
					text = error,
					color = MaterialTheme.colorScheme.error,
					style = MaterialTheme.typography.bodySmall,
					modifier = Modifier.padding(top = 8.dp),
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			Button(
				onClick = { viewModel.saveItem() },
				enabled = !uiState.isLoading && uiState.isLoaded,
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp),
				colors = ButtonDefaults.buttonColors(containerColor = LookeoGold),
				shape = RoundedCornerShape(12.dp),
			) {
				if (uiState.isLoading) {
					CircularProgressIndicator(
						color = LookeoBlack,
						modifier = Modifier.size(24.dp),
						strokeWidth = 2.dp,
					)
				} else {
					Text("Guardar cambios", color = LookeoBlack)
				}
			}
		}
	}
}
