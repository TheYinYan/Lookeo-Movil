package com.alanturing.lookeo_movil.features.items.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class NewItemViewModel @Inject constructor() : ViewModel(){
    private val _uiState = MutableStateFlow(NewItemUiState())
    val uiState: StateFlow<NewItemUiState> = _uiState.asStateFlow()

    fun onTipoChanged(newTipo: String) {
        _uiState.update { it.copy(tipo = newTipo) }
    }

    fun onColorChanged(newColor: String){
        _uiState.update { it.copy(color = newColor) }
    }

    fun onTemporadaChanged(newTemporada: String){
        _uiState.update { it.copy(temporada = newTemporada) }
    }

    fun onOcasionChanged(newOcasion: String){
        _uiState.update { it.copy(ocasion = newOcasion) }
    }

    fun onNotasChanged(newNotas: String){
        _uiState.update { it.copy(notas = newNotas) }
    }

    fun saveItem() {
        // Aquí iría tu lógica para guardar en Room, Firebase o Retrofit
        println("Guardando prenda: ${_uiState.value}")
    }
}

data class NewItemUiState(
    val tipo: String = "Camisa",
    val color: String = "Blanco",
    val temporada: String = "Entretiempo",
    val ocasion: String = "Casual",
    val notas: String = "",
    val isLoading: Boolean = false
)