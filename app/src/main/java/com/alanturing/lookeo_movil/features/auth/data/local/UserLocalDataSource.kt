package com.alanturing.lookeo_movil.features.auth.data.local

import com.alanturing.lookeo_movil.features.auth.data.model.User
import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    val users: Flow<List<User>>

    fun observeUser(id: Long): Flow<User?>

    suspend fun saveUser(user: User)

    suspend fun saveUsers(users: List<User>)
}