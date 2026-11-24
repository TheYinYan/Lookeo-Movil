package com.alanturing.lookeo_movil.features.auth.data.repository

import com.alanturing.lookeo_movil.features.auth.data.local.datasource.TokenLocalDataSource
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.UserLocalDataSource
import com.alanturing.lookeo_movil.features.auth.data.model.User
import com.alanturing.lookeo_movil.features.auth.data.remote.UserRemoteDataSource
import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserSession
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val _remoteDataSource: UserRemoteDataSource,
    private val _localDataSource: UserLocalDataSource,
    private val _tokenLocalDataSource: TokenLocalDataSource,
) : UserRepository {

    override fun observeUser(id: Int): Flow<User?> = _localDataSource.observeUser(id)
    override fun observeUser(): Flow<User?> =
        _localDataSource.observeUser()

    override suspend fun getToken(): String? =
        _tokenLocalDataSource.getToken()

    override suspend fun login(email: String, password: String): Result<UserSession>  {
        val result = _remoteDataSource.login(email, password)

        result.onSuccess { session ->
            saveSession(session)
        }

        return result
    }

    override suspend fun register(email: String, name: String, password: String): Result<UserSession> {
        val result = _remoteDataSource.register(email, name, password)

        result.onSuccess { session ->
            saveSession(session)
        }

        return result
    }

    private suspend fun saveSession(session: UserSession) {
        _localDataSource.saveUser(session.user)
        _tokenLocalDataSource.saveToken(session.token)
    }
}
