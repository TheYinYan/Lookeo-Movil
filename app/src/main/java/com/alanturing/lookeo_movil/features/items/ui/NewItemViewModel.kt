package com.alanturing.lookeo_movil.features.items.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.lookeo_movil.features.items.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(NewItemUiState())
    val uiState: StateFlow<NewItemUiState> = _uiState.asStateFlow()

    fun onTipoChanged(newTipo: String) {
        _uiState.update { it.copy(tipo = newTipo, errorMessage = null) }
    }

    fun onColorChanged(newColor: String){
        _uiState.update { it.copy(color = newColor, errorMessage = null) }
    }

    fun onTemporadaChanged(newTemporada: String){
        _uiState.update { it.copy(temporada = newTemporada, errorMessage = null) }
    }

    fun onOcasionChanged(newOcasion: String){
        _uiState.update { it.copy(ocasion = newOcasion, errorMessage = null) }
    }

    fun onNotasChanged(newNotas: String){
        _uiState.update { it.copy(notas = newNotas, errorMessage = null) }
    }

    fun saveItem() {
        val current = _uiState.value
        if (current.isLoading) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val  result = itemRepository.createItem(
                tipo = current.tipo,
                color = current.color,
                temporada = current.temporada,
                ocasion = current.ocasion,
                // Falta imageUrl para cuando este la camara
                notas = current.notas.trim(),
            )

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Error al guardar la prenda",
                        )
                    }
                },
            )
        }
    }
    fun resetSuccessState() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}

data class NewItemUiState(
    val tipo: String = "Selecciona",
    val color: String = "Selecciona",
    val temporada: String = "Selecciona",
    val ocasion: String = "Selecciona",
    val notas: String = "",
    // Falta imageUrl para cuando este la camara
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
)