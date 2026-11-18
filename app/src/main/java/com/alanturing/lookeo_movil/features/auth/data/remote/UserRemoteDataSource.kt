package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.User

interface UserRemoteDataSource {
    suspend fun readAllUser(): List<com.alanturing.lookeo_movil.features.auth.data.model.User>
    suspend fun readUserById(id: Long): com.alanturing.lookeo_movil.features.auth.data.model.User?
}