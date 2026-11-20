package com.alanturing.lookeo_movil.features.auth.data.remote.dto

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: String
)