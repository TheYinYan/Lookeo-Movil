package com.alanturing.lookeo_movil.features.auth.data.repository

import com.alanturing.lookeo_movil.features.auth.data.model.User
import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserSession
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun observeUser(id: Int): Flow<User?>
    fun observeUser(): Flow<User?>

    suspend fun getToken(): String?

    suspend fun login(email: String, password: String): Result<UserSession>
    suspend fun register(email: String, name: String, password: String): Result<UserSession>
}