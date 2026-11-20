package com.alanturing.lookeo_movil.features.auth.data.remote.dto

data class AuthResponse(
    val user: UserResponse,
    val token: String
)