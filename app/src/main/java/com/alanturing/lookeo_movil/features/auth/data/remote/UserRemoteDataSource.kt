package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterResponse

interface UserRemoteDataSource {
    suspend fun readAllUser(): List<com.alanturing.lookeo_movil.features.auth.data.model.User>
    suspend fun readUserById(id: Long): com.alanturing.lookeo_movil.features.auth.data.model.User?
    suspend fun login(email: String, password: String): Result<LoginResponse>
    suspend fun register(email: String, name: String, password: String): Result<RegisterResponse>
}