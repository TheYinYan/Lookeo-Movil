package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.core.data.remote.model.HealthResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.dto.AuthResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserSession
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LookeoAuthApi {

    @POST("api/auth/register/")
    suspend fun register(@Body body: RegisterBody): AuthResponse

    @POST("api/auth/login/")
    suspend fun login(@Body body: LoginBody): AuthResponse


}
