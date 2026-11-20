package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.model.User
import com.alanturing.lookeo_movil.core.data.remote.LookeoApi
import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterResponse
import javax.inject.Inject

class UserRemoteDataSourceImpl
    @Inject constructor(
    private val api: LookeoApi
) : UserRemoteDataSource {

    override suspend fun readAllUser(): List<User> {
        return emptyList()
    }

    override suspend fun readUserById(id: Long): User? {
        return null
    }

    override suspend fun register(email: String, name: String, password: String): Result<RegisterResponse> {
        val body = RegisterBody(email, name, password)
        return api.register(body)
    }

    override suspend fun login(email: String, password: String): Result<LoginResponse> {
        val body = LoginBody(email, password)
        return api.login(body)
    }

}