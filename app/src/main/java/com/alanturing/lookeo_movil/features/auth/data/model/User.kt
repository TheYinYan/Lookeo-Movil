package com.alanturing.lookeo_movil.features.auth.data.model

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val imageUrl: String?
)