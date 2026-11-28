package com.alanturing.lookeo_movil.core.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.alanturing.lookeo_movil.features.items.data.model.Item
import com.alanturing.lookeo_movil.features.items.data.model.SyncStatus

@Entity(tableName = "item")
data class ItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val userId: Int,
    val tipo: String,
    val color: String,
    val temporada: String,
    val ocasion: String,
    val notas: String,
    val imageUrl: String?,
    val createdAt: Long,
	val syncStatus: String = SyncStatus.SYNCED.name,
)

fun ItemEntity.toModel(): Item = Item(
    id = id,
    userId = userId,
    tipo = tipo,
    color = color,
    temporada = temporada,
    ocasion = ocasion,
    notas = notas,
    imageUrl = imageUrl,
    createdAt = createdAt,
	syncStatus = SyncStatus.valueOf(syncStatus),
)

fun Item.toEntity(): ItemEntity = ItemEntity(
    id = id,
    userId = userId,
    tipo = tipo,
    color = color,
    temporada = temporada,
    ocasion = ocasion,
    notas = notas,
    imageUrl = imageUrl,
    createdAt = createdAt,
	syncStatus = syncStatus.name,
)