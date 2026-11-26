package com.alanturing.lookeo_movil.features.items.data.local.datasource

import com.alanturing.lookeo_movil.core.data.local.ItemDao
import com.alanturing.lookeo_movil.core.data.local.toEntity
import com.alanturing.lookeo_movil.core.data.local.toModel
import com.alanturing.lookeo_movil.features.items.data.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemLocalDBDatasource @Inject constructor(
    private val dao: ItemDao,
) : ItemLocalDataSource {

    override fun observeItemsByUser(userId: Int): Flow<List<Item>> {
        return dao.observeByUser(userId).map { entities ->
            entities.map { it.toModel() }
        }
    }

    override fun observeItemById(id: Int): Flow<Item?> {
        return dao.observeById(id).map { entity ->
            entity?.toModel()
        }
    }

    override suspend fun saveItem(item: Item) {
        dao.upsert(item.toEntity())
    }

    override suspend fun saveItems(items: List<Item>) {
        dao.upsertAll(items.map { it.toEntity() })
    }

    override suspend fun clearItemsByUser(userId: Int) {
        dao.clearByUser(userId)
    }

    override suspend fun deleteItemById(id: Int) {
        dao.deleteById(id)
    }
}
