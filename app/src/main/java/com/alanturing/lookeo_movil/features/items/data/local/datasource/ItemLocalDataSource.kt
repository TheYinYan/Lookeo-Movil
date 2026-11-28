package com.alanturing.lookeo_movil.features.items.data.local.datasource

import com.alanturing.lookeo_movil.features.items.data.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemLocalDataSource {

    fun observeItemsByUser(userId: Int): Flow<List<Item>>

    fun observeItemById(id: Int): Flow<Item?>

    suspend fun saveItem(item: Item)

    suspend fun saveItems(items: List<Item>)

    suspend fun clearItemsByUser(userId: Int)

    suspend fun deleteItemById(id: Int)

	suspend fun getPendingItems(): List<Item>

	suspend fun clearSyncedItemsByUser(userId: Int)
}
