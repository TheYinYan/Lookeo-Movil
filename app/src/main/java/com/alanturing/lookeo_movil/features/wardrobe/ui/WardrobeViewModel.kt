package com.alanturing.lookeo_movil.features.wardrobe.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.lookeo_movil.core.data.network.NetworkMonitor
import com.alanturing.lookeo_movil.features.items.data.model.Item
import com.alanturing.lookeo_movil.features.items.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WardrobeViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
	networkMonitor: NetworkMonitor,
) : ViewModel() {

	private val _filters = MutableStateFlow(WardrobeFilters())
	val filters: StateFlow<WardrobeFilters> = _filters.asStateFlow()

	val filteredItems: StateFlow<List<Item>> =
		combine(itemRepository.observeItems(), _filters) { items, filters ->
			items.filter { item ->
				(filters.tipo == null || item.tipo == filters.tipo) &&
				(filters.color == null || item.color == filters.color) &&
				(filters.temporada == null || item.temporada == filters.temporada) &&
				(filters.ocasion == null || item.ocasion == filters.ocasion)
			}
		}.stateIn(
			scope = viewModelScope,
			started = SharingStarted.WhileSubscribed(5000),
			initialValue = emptyList(),
		)

	fun applyFilters(newFilters: WardrobeFilters) {
		_filters.value = newFilters
	}

	fun clearFilters() {
		_filters.value = WardrobeFilters()
	}

	init { 
		refreshItems() 

		viewModelScope.launch {
			networkMonitor.isOnLine.collect { online ->
				if (online) {
					itemRepository.syncPending()
				}
			}
		}
	}

	fun refreshItems() {
		viewModelScope.launch {
			itemRepository.syncPending()
			itemRepository.refresh()
		}
	}
}

data class WardrobeFilters(
	val tipo: String? = null,
	val color: String? = null,
	val temporada: String? = null,
	val ocasion: String? = null,
) {
	val hasActiveFilters: Boolean
		get() = tipo != null || color != null || temporada != null || ocasion != null
}