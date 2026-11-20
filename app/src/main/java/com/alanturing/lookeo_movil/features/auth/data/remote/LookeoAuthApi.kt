package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LookeoAuthApi {

    @POST("api/register/")
    suspend fun register(@Body body: RegisterBody):Result<RegisterResponse>

    @POST("api/login/")
    suspend fun login(@Body body: LoginBody):Result<LoginResponse>
}
