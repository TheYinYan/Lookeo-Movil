package com.alanturing.lookeo_movil.features.items.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.alanturing.lookeo_movil.core.navigation.Routes
import com.alanturing.lookeo_movil.features.items.data.model.Item
import com.alanturing.lookeo_movil.features.items.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ItemDetailUiState(
	val itemId: Int = 0,
	val userId: Int = 0,
	val tipo: String = "",
	val color: String = "",
	val temporada: String = "",
	val ocasion: String = "",
	val notas: String = "",
	val imageUrl: String? = null,
	val createdAt: Long = 0L,
	val isLoading: Boolean = false,
	val isLoaded: Boolean = false,
	val errorMessage: String? = null,
	val isSuccess: Boolean = false,
)

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
	private val itemRepository: ItemRepository,
	SavedStateHandle: SavedStateHandle,
) : ViewModel() {

	private val route = SavedStateHandle.toRoute<Routes.DetailItem>()
	private val itemId: Int = route.itemId.toIntOrNull() ?: 0

	private val _uiState = MutableStateFlow(ItemDetailUiState(itemId = itemId))
	val uiState: StateFlow<ItemDetailUiState> = _uiState.asStateFlow()

	init {
		if (itemId > 0) {
			viewModelScope.launch {
				itemRepository.observeItem(itemId).collect { item ->
					if (item != null) {
						_uiState.update {
							it.copy(
								userId = item.userId,
								tipo = item.tipo,
								color = item.color,
								temporada = item.temporada,
								ocasion = item.ocasion,
								notas = item.notas,
								imageUrl = item.imageUrl,
								createdAt = item.createdAt,
								isLoaded = true,
							)
						}
					}
				}
			}
		} else {
			_uiState.update { it.copy(errorMessage = "Prenda no válida") }
		}
	}
	

	fun onTipoChanged(value: String) {
		_uiState.update { it.copy(tipo = value, errorMessage = null) }
	}

	fun onColorChanged(value: String) {
		_uiState.update { it.copy(color = value, errorMessage = null) }
	}

	fun onTemporadaChanched(value: String) {
		_uiState.update { it.copy(temporada = value, errorMessage = null) }
	}

	fun onOcasionChanched(value: String) {
		_uiState.update { it.copy(notas = value, errorMessage = null) }
	}

	fun NotasChanged(value: String) {
		_uiState.update { it.copy(notas = value, errorMessage = null) }
	}

	fun saveItem() {
		val current = _uiState.value
		
		if (current.isLoading || !current.isLoaded) return

		viewModelScope.launch {
			_uiState.update { it.copy(isLoading = true, errorMessage = null) }

			val item = Item(
				id = current.itemId,
				userId = current.userId,
				tipo = current.tipo,
				color = current.color,
				temporada = current.temporada,
				ocasion = current.ocasion,
				notas = current.notas.trim(),
				imageUrl = current.imageUrl,
				createdAt = current.createdAt,
			)

			itemRepository.updateItem(item).fold(
				onSuccess = {
					_uiState.update { it.copy(isLoading = false, isSuccess= true) }
				},
				onFailure = { error ->
					_uiState.update {
						it.copy(
							isLoading = false,
							errorMessage = error.message ?: "Error al actualizar la prenda",
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