package com.alanturing.lookeo_movil.features.auth.data.local

interface TokenLocalDataSource {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun clearToken()
}