package com.alanturing.lookeo_movil.core.data.remote

import com.alanturing.lookeo_movil.core.data.remote.model.HealthResponse

interface RemoteDataSource {

    suspend fun checkHealth(): HealthResponse
}