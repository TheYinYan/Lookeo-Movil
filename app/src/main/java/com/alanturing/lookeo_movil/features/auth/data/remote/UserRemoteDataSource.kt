package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.remote.dto.AuthResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserSession

interface UserRemoteDataSource {
    suspend fun readAllUser(): List<com.alanturing.lookeo_movil.features.auth.data.model.User>
    suspend fun readUserById(id: Long): com.alanturing.lookeo_movil.features.auth.data.model.User?
    suspend fun login(email: String, password: String): Result<UserSession>
    suspend fun register(email: String, name: String, password: String): Result<UserSession>
}