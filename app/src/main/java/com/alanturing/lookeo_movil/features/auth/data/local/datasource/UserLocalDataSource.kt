package com.alanturing.lookeo_movil.features.auth.data.local.datasource

import com.alanturing.lookeo_movil.features.auth.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    fun observeUser(id: Long): Flow<User?>

    fun observeUser(): Flow<User?>

    suspend fun saveUser(user: User)
}