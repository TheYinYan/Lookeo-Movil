package com.alanturing.lookeo_movil.features.auth.data.user.local

import com.alanturing.lookeo_movil.features.auth.data.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    val users: Flow<List<User>>
        get() = TODO("Not yet implemented")

    fun observeUser(id: Long): Flow<User?> {
        TODO("Not yet implemented")
    }

    suspend fun save(users: List<User>) {
        TODO("Not yet implemented")
    }
}