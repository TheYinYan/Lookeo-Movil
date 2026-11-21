package com.alanturing.lookeo_movil.core.data.remote

import com.alanturing.lookeo_movil.core.data.remote.model.HealthResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: LookeoApi
) : RemoteDataSource {

    override suspend fun checkHealth(): HealthResponse {
        return api.checkHealth()
    }

}