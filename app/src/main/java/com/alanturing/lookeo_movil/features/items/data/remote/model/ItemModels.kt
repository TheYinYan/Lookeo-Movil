package com.alanturing.lookeo_movil.features.items.data.remote.model

import com.alanturing.lookeo_movil.features.items.data.model.Item
import com.google.gson.annotations.SerializedName

data class CreateItemBody(
    val type: String,
    val color: List<String>,
    val season: List<String>,
    val style: List<String>,
    val imgUrl: List<String> = emptyList(),
    val notes: String? = null,
)

data class ItemResponse(
    val id: Int,
    @SerializedName("userId", alternate = ["user_id"])
    val userId: Int = 0,
    val type: String,
    val color: List<String> = emptyList(),
    val season: List<String> = emptyList(),
    val style: List<String> = emptyList(),
    val imgUrl: List<String> = emptyList(),
    val notes: String? = null,
    val createdAt: String? = null,
)

fun ItemResponse.toModel(): Item = Item(
    id = id,
    userId = userId,
    tipo = type,
    color = color.joinToString(", "),
    temporada = season.joinToString(", "),
    ocasion = style.joinToString(", "),
    notas = notes.orEmpty(),
    imageUrl = imgUrl.firstOrNull(),
    createdAt = System.currentTimeMillis(),
)
