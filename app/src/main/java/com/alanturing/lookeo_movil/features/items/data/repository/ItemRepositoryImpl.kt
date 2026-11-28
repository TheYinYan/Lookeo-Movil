package com.alanturing.lookeo_movil.features.items.data.repository

import com.alanturing.lookeo_movil.features.auth.data.repository.UserRepository
import com.alanturing.lookeo_movil.features.items.data.local.datasource.ItemLocalDataSource
import com.alanturing.lookeo_movil.features.items.data.model.Item
import com.alanturing.lookeo_movil.features.items.data.remote.ItemRemoteDataSource
import com.alanturing.lookeo_movil.features.items.data.remote.model.CreateItemBody
import com.alanturing.lookeo_movil.features.items.data.model.SyncStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
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

	override fun observeItem(id: Int): Flow<Item?> {
		return localDataSource.observeItemById(id)
	}

	override suspend fun updateItem(item: Item): Result<Item> {
		val token = userRepository.getToken()
		if (token.isNullOrBlank()) {
			return Result.failure(Exception("No hay sesión activa"))
		}

		if (item.id < 0) {
			val local = item.copy(syncStatus = SyncStatus.PENDING_CREATE)
			localDataSource.saveItem(local)
			return Result.success(local)
		}

		val body = buildBody(
			tipo = item.tipo,
			color = item.color,
			temporada = item.temporada,
			ocasion = item.ocasion,
			notas = item.notas,
			imageUrl = item.imageUrl,
		)
		val result = remoteDataSource.updateItem(item.id, body)

		return result.fold(
			onSuccess = { updated ->
				val toSave = updated.copy(
					userId = if (updated.userId <= 0) item.userId else updated.userId,
					syncStatus = SyncStatus.SYNCED,
				)
				localDataSource.saveItem(toSave)
				Result.success(toSave)
			},
			onFailure = {
				val pending = item.copy(syncStatus = SyncStatus.PENDING_UPDATE)
				localDataSource.saveItem(pending)
				Result.success(pending)
			},
		)
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

		val user = userRepository.observeUser().filterNotNull().firstOrNull()
			?: return Result.failure(Exception("No hay usuario en sesión"))

		val body = buildBody(tipo, color, temporada, ocasion, notas, imageUrl)
		val result = remoteDataSource.createItem(body)

		return result.fold(
			onSuccess = { created ->
				val toSave = created.copy(
					userId = if (created.userId <= 0) user.id else created.userId,
					syncStatus = SyncStatus.SYNCED,
				)
				localDataSource.saveItem(toSave)
				Result.success(toSave)
			},
			onFailure = {
				val local = Item(
					id = nextLocalId(),
					userId = user.id,
					tipo = tipo,
					color = color,
					temporada = temporada,
					ocasion = ocasion,
					notas = notas,
					imageUrl = imageUrl ?: DEFAULT_ITEM_IMAGE_URL,
					syncStatus = SyncStatus.PENDING_CREATE,
				)
				localDataSource.saveItem(local)
				Result.success(local)
			}
		)
	}

    override suspend fun refresh(): Result<Unit> {
        val user = userRepository.observeUser().filterNotNull().firstOrNull()
            ?: return Result.failure(Exception("No hay usuario en sesión"))

        val result = remoteDataSource.getMyItems()
        return result.map { items ->
            val itemsWithUser = items.map { item ->
                if (item.userId <= 0) item.copy(userId = user.id) else item
            }
            localDataSource.clearSyncedItemsByUser(user.id)
            localDataSource.saveItems(itemsWithUser)
        }
    }

	override suspend fun syncPending(): Result<Unit> {
		val pending = localDataSource.getPendingItems()

		for (item in pending) {
			when (item.syncStatus) {
				SyncStatus.PENDING_CREATE -> {
					val body = buildBody(
						tipo = item.tipo,
						color = item.color,
						temporada = item.temporada,
						ocasion = item.ocasion,
						notas = item.notas,
						imageUrl = item.imageUrl,
					)
					remoteDataSource.createItem(body).onSuccess { created ->
						localDataSource.deleteItemById(item.id)
						localDataSource.saveItem(
							created.copy(
								userId = if (created.userId <= 0) item.userId else created.userId,
								syncStatus = SyncStatus.SYNCED,
							)
						)
					}
				}

				SyncStatus.PENDING_UPDATE -> {
					val body = buildBody(
						tipo = item.tipo,
						color = item.color,
						temporada = item.temporada,
						ocasion = item.ocasion,
						notas = item.notas,
						imageUrl = item.imageUrl,
					)
					remoteDataSource.updateItem(item.id, body).onSuccess { updated ->
						localDataSource.saveItem(
							updated.copy(
								userId = if (updated.userId <= 0) item.userId else updated.userId,
								syncStatus = SyncStatus.SYNCED,
							)
						)
					}
				}
				SyncStatus.SYNCED -> Unit
			}
		}
		return Result.success(Unit)
	}

	private fun buildBody(
		tipo: String,
		color: String,
		temporada: String,
		ocasion: String,
		notas: String,
		imageUrl: String?,
	): CreateItemBody {
		val resolvedImgUrl = if (imageUrl.isNullOrBlank()) {
			listOf(DEFAULT_ITEM_IMAGE_URL)
		} else {
			listOf(imageUrl)
		}
		return CreateItemBody(
			type = tipo,
			color = listOf(color),
			season = listOf(temporada),
			style = listOf(ocasion),
			imgUrl = resolvedImgUrl,
			notes = notas.ifBlank { null },
		)
	}

	private suspend fun nextLocalId(): Int {
		val pendingCreates = localDataSource.getPendingItems()
			.filter { it.syncStatus == SyncStatus.PENDING_CREATE }
		val minId = pendingCreates.minOfOrNull { it.id } ?: 0
		return if (minId < 0) minId - 1 else -1
	}
}
