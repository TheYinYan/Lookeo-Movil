package com.alanturing.lookeo_movil.features.auth.data.repository

import com.alanturing.lookeo_movil.features.auth.data.local.datasource.TokenLocalDataSource
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.UserLocalDataSource
import com.alanturing.lookeo_movil.features.auth.data.model.User
import com.alanturing.lookeo_movil.features.auth.data.remote.UserRemoteDataSource
import com.alanturing.lookeo_movil.features.auth.data.remote.model.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val _remoteDataSource: UserRemoteDataSource,
    private val _localDataSource: UserLocalDataSource,
    private val _tokenLocalDataSource: TokenLocalDataSource,
) : UserRepository {

    override val users: Flow<List<User>> = _localDataSource.users
    override fun observeUser(id: Long): Flow<User?> = _localDataSource.observeUser(id)

    init {
        runBlocking {
            refresh()
        }
    }

    suspend fun refresh() {
        val remoteUsers = _remoteDataSource.readAllUser()
        _localDataSource.saveUsers(remoteUsers)
    }

    override suspend fun readAllUser(): List<User> {
        return _remoteDataSource.readAllUser()
    }

    override suspend fun readUserById(id: Long): User? {
        return _remoteDataSource.readUserById(id)
    }

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
