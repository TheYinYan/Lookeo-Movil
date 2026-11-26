package com.alanturing.lookeo_movil.features.items.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.alanturing.lookeo_movil.core.ui.components.LookeoTopBar
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoDarkSurface
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite

@Composable
fun NewItemScreen(
    onNavigateToWardrobe: () -> Unit,
    viewModel: NewItemViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            viewModel.resetSuccessState()
            onNavigateToWardrobe()
        }
    }

    Scaffold(
        topBar = {
            LookeoTopBar(onNavigateToWardrobe)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LookeoBlack)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Haz espacio para tu estilo", color = LookeoGold, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 12.dp))
            Text("Crear prenda", color = LookeoWhite, style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(bottom = 12.dp))
            Text("Fotografía, etiqueta y añade a tu ropero.", color = LookeoGray, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 12.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Text("Etiquetas sugeridas. Ajustalas a tu gusto.", color = LookeoGold, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DropdownField(
                    label = "Tipo",
                    selectedOption = uiState.tipo,
                    options = listOf("Camisa", "Pantalón", "Zapatos", "Abrigo"),
                    onOptionSelected = { viewModel.onTipoChanged(it) },
                    modifier = Modifier.weight(1f)
                )
                DropdownField(
                    label = "Color",
                    selectedOption = uiState.color,
                    options = listOf("Blanco", "Negro", "Azul", "Rojo"),
                    onOptionSelected = { viewModel.onColorChanged(it) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DropdownField(
                    label = "Temporada",
                    selectedOption = uiState.temporada,
                    options = listOf("Primavera", "Verano", "Otoño", "Invierno"),
                    onOptionSelected = { viewModel.onTemporadaChanged(it) },
                    modifier = Modifier.weight(1f)
                )
                DropdownField(
                    label = "Ocasión",
                    selectedOption = uiState.ocasion,
                    options = listOf("Casual", "Formal", "Deportivo"),
                    onOptionSelected = { viewModel.onOcasionChanged(it) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.notas,
                onValueChange = { viewModel.onNotasChanged(it) },
                placeholder = { Text("Notas adicionales...", color = LookeoGray, fontSize = 12.sp) },
                modifier = Modifier.fillMaxWidth().height(100.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LookeoDarkSurface,
                    unfocusedContainerColor = LookeoDarkSurface,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = LookeoWhite,
                    unfocusedTextColor = LookeoWhite
                ),
                shape = RoundedCornerShape(12.dp),
                textStyle = TextStyle(fontSize = 12.sp)
            )

            uiState.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            val isFormValid = uiState.tipo != "Selecciona" &&
                    uiState.color != "Selecciona" &&
                    uiState.temporada != "Selecciona" &&
                    uiState.ocasion != "Selecciona"

            Button(
                onClick = { viewModel.saveItem() },
                enabled = !uiState.isLoading && isFormValid,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LookeoGold),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        color = LookeoBlack,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Guardar prenda", color = LookeoBlack)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(text = label, color = LookeoGray, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 4.dp))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = LookeoDarkSurface,
                    unfocusedContainerColor = LookeoDarkSurface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = LookeoWhite,
                    unfocusedTextColor = LookeoWhite
                ),
                shape = RoundedCornerShape(12.dp),
                textStyle = TextStyle(fontSize = 14.sp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(LookeoBlack)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, color = LookeoWhite) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
