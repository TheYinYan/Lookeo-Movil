package com.alanturing.lookeo_movil.features.auth.data.remote.model

import com.alanturing.lookeo_movil.features.auth.data.model.User
import java.util.Date

data class RegisterBody(
    val email: String,
    val name: String,
    val password: String,
)

data class RegisterResponse(
    val user: UserResponse,
    val token: String,
)

data class LoginBody(
    val email: String,
    val password: String,
)

data class LoginResponse(
    val user: UserResponse,
    val token: String,
)

data class UserSession(
    val user: User,
    val token: String
)

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val createdAt: String,
)