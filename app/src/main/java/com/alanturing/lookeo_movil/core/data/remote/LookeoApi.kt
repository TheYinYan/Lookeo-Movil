package com.alanturing.lookeo_movil.core.data.remote

import com.alanturing.lookeo_movil.features.auth.data.remote.LookeoAuthApi
import com.alanturing.lookeo_movil.core.data.remote.model.HealthResponse
import com.alanturing.lookeo_movil.features.items.data.remote.LookeoItemsApi
import retrofit2.http.GET

interface LookeoApi : LookeoAuthApi, LookeoItemsApi  {

    @GET("health")
    suspend fun checkHealth(): HealthResponse

}

