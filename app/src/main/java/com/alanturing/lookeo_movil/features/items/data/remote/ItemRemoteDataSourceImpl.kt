package com.alanturing.lookeo_movil.features.items.data.remote

import com.alanturing.lookeo_movil.core.data.remote.LookeoApi
import com.alanturing.lookeo_movil.features.items.data.model.Item
import com.alanturing.lookeo_movil.features.items.data.remote.model.CreateItemBody
import com.alanturing.lookeo_movil.features.items.data.remote.model.toModel
import javax.inject.Inject

class ItemRemoteDataSourceImpl @Inject constructor(
    private val api: LookeoApi,
) : ItemRemoteDataSource {

    override suspend fun getMyItems(): Result<List<Item>> {
        return try {
            val response = api.getMyItems()
            if (response.isSuccessful) {
                val items = response.body()?.map { it.toModel() }.orEmpty()
                Result.success(items)
            } else {
                val errorDetail = response.errorBody()?.string() ?: "Sin detalle"
                Result.failure(Exception("Error ${response.code()}: $errorDetail"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createItem(body: CreateItemBody): Result<Item> {
        return try {
            val response = api.createItem(body)
            val created = response.body()
            if (response.isSuccessful && created != null) {
                Result.success(created.toModel())
            } else {
                val errorDetail = response.errorBody()?.string() ?: "Sin detalle"
                Result.failure(Exception("Error ${response.code()}: $errorDetail"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateItem(id: Int, body: CreateItemBody): Result<Item> {
        return try {
            val response = api.updateItem(id, body)
            val created = response.body()
            if (response.isSuccessful && created != null) {
                Result.success(created.toModel())
            } else {
                val errorDetail = response.errorBody()?.string() ?: "Sin detalle"
                Result.failure(Exception("Error ${response.code()}: $errorDetail"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
