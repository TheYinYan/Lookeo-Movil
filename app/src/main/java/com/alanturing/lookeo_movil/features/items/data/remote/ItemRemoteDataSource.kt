package com.alanturing.lookeo_movil.features.items.data.remote

import com.alanturing.lookeo_movil.features.items.data.model.Item
import com.alanturing.lookeo_movil.features.items.data.remote.model.CreateItemBody

interface ItemRemoteDataSource {
    suspend fun getMyItems(): Result<List<Item>>
    suspend fun createItem(body: CreateItemBody): Result<Item>
}
