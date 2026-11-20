package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserSession

interface UserRemoteDataSource {

    suspend fun login(email: String, password: String): Result<UserSession>
    suspend fun register(email: String, name: String, password: String): Result<UserSession>
}