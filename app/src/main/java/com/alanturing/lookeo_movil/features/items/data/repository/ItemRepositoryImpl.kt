package com.alanturing.lookeo_movil.features.items.data.repository

import com.alanturing.lookeo_movil.features.auth.data.repository.UserRepository
import com.alanturing.lookeo_movil.features.items.data.local.datasource.ItemLocalDataSource
import com.alanturing.lookeo_movil.features.items.data.model.Item
import com.alanturing.lookeo_movil.features.items.data.remote.ItemRemoteDataSource
import com.alanturing.lookeo_movil.features.items.data.remote.model.CreateItemBody
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val remoteDataSource: ItemRemoteDataSource,
    private val localDataSource: ItemLocalDataSource,
    private val userRepository: UserRepository,
) : ItemRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observeItems(): Flow<List<Item>> {
        return userRepository.observeUser().flatMapLatest { user ->
            if (user == null) {
                flowOf(emptyList())
            } else {
                localDataSource.observeItemsByUser(user.id)
            }
        }
    }

    companion object {
        const val DEFAULT_ITEM_IMAGE_URL =
            "https://images.unsplash.com/photo-1782227286076-4f5153a8cdac?q=80&w=870&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=NHwxMjA3fDA%3D"
    }

    override suspend fun createItem(
        tipo: String,
        color: String,
        temporada: String,
        ocasion: String,
        notas: String,
        imageUrl: String?,
    ): Result<Item> {
        val token = userRepository.getToken()
        if (token.isNullOrBlank()) {
            return Result.failure(Exception("No hay sesión activa"))
        }

        val resolvedImgUrl: List<String> = if (imageUrl.isNullOrBlank()) {
            listOf(DEFAULT_ITEM_IMAGE_URL)
        } else {
            listOf(imageUrl)
        }

        val body = CreateItemBody(
            type = tipo,
            color = listOf(color),
            season = listOf(temporada),
            style = listOf(ocasion),
            imgUrl = resolvedImgUrl,
            notes = notas.ifBlank { null },
        )

        val result = remoteDataSource.createItem(body)
        result.onSuccess { createdItem ->
            localDataSource.saveItem(createdItem)
        }
        return result
    }

    override suspend fun refresh(): Result<Unit> {
        val user = userRepository.observeUser().firstOrNull()
            ?: return Result.failure(Exception("No hay usuario en sesión"))

        val result = remoteDataSource.getMyItems()
        return result.map { items ->
            localDataSource.clearItemsByUser(user.id)
            localDataSource.saveItems(items)
        }
    }
}
