package com.alanturing.lookeo_movil.features.items.data.repository

import com.alanturing.lookeo_movil.features.items.data.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun observeItems(): Flow<List<Item>>

	fun observeItem(id: Int): Flow<Item?>

    suspend fun createItem(
        tipo: String,
        color: String,
        temporada: String,
        ocasion: String,
        notas: String,
        imageUrl: String? = null,
    ): Result<Item>

    suspend fun updateItem(item: Item): Result<Item>

    suspend fun refresh(): Result<Unit>

	suspend fun syncPending(): Result<Unit>
}
