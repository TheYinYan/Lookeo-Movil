package com.alanturing.lookeo_movil.core.data.remote

import retrofit2.http.GET

interface LookeoApi {

    @GET("health")
    suspend fun checkHealth(): HealthResponse


}