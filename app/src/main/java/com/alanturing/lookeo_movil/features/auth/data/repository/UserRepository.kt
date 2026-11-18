package com.alanturing.lookeo_movil.features.auth.data.repository

import com.alanturing.lookeo_movil.features.auth.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val users: Flow<List<User>>
    fun observeUser(id: Long): Flow<User?>
    suspend fun readAllUser(): List<User>
    suspend fun readUserById(id: Long): User?
}