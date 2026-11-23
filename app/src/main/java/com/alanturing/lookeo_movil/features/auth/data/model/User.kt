package com.alanturing.lookeo_movil.features.auth.data.model

import java.util.Date

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val createdAt: Date,
    val imageUrl: String?
)