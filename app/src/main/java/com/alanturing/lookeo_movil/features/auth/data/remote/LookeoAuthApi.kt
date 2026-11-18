package com.alanturing.lookeo_movil.features.auth.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface LookeoAuthApi {

    @POST("api/login/")
    suspend fun login(@Body body: LoginBody):Result<LoginResponse>
    @POST("api/register/")
    suspend fun register()
}

data class LoginBody(
    val email:String,
    val password: String,
)

data class LoginResponse(
    val user:String,
    val token:String,
)