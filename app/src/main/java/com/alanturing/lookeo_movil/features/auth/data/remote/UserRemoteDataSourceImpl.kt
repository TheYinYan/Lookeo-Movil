package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.model.User
import com.alanturing.lookeo_movil.core.data.remote.LookeoApi
import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserResponse
import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserSession
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

    override suspend fun register(email: String, name: String, password: String): Result<UserSession> {
        val body = RegisterBody(email, name, password)
        return api.register(body).map { response ->
            UserSession(
                user = response.user.toModel(),
                token = response.token
            )
        }
    }

    override suspend fun login(email: String, password: String): Result<UserSession> {
        val body = LoginBody(email, password)
        return api.login(body).map { response ->
            UserSession(
                user = response.user.toModel(),
                token = response.token
            )
        }
    }
}

fun UserResponse.toModel(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email,
        createdAt = this.createdAt,
        imageUrl = null
    )
}