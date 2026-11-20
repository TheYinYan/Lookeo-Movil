package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.model.User
import com.alanturing.lookeo_movil.core.data.remote.LookeoApi
import com.alanturing.lookeo_movil.features.auth.data.remote.model.LoginBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.RegisterBody
import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserSession
import com.alanturing.lookeo_movil.features.auth.data.remote.dto.UserResponse
import java.util.Date
import java.time.Instant
import javax.inject.Inject

class UserRemoteDataSourceImpl
    @Inject constructor(
    private val api: LookeoApi
) : UserRemoteDataSource {


    override suspend fun register(email: String, name: String, password: String): Result<UserSession> {
        val body = RegisterBody(email, name, password)
        return runCatching {
            val response = api.register(body)

            UserSession(
                user = response.user.toModel(),
                token = response.token
            )
        }
    }

    override suspend fun login(email: String, password: String): Result<UserSession> {
        val body = LoginBody(email, password)
        return runCatching {
            val response = api.login(body)

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
        createdAt = Date.from(Instant.parse(this.createdAt)),
        imageUrl = null
    )
}