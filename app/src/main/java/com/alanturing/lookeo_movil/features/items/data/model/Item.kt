package com.alanturing.lookeo_movil.features.items.data.model

data class Item(
    val id: Int,
    val userId: Int,
    val tipo: String,
    val color: String,
    val temporada: String,
    val ocasion: String,
    val notas: String,
    val imageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
	val syncStatus: SyncStatus = SyncStatus.SYNCED,
)