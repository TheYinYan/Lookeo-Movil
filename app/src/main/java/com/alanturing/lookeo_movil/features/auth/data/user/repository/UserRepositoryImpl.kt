package com.alanturing.lookeo_movil.features.auth.data.user.repository

import com.alanturing.lookeo_movil.features.auth.data.user.model.User
import com.alanturing.lookeo_movil.features.auth.data.user.local.UserLocalDataSource
import kotlinx.coroutines.flow.Flow
// import kotlinx.coroutines.runBlocking

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    // private val _remoteDataSource: UserRemoteDataSource,
    private val _localDataSource: UserLocalDataSource,
): UserRepository {

    override val users: Flow<List<User>> = _localDataSource.users
    override fun observeUser(id: Long): Flow<User?> = _localDataSource.observeUser(id)

    /* init {
        runBlocking {
            refresh()
        }
    } */

    suspend fun refresh() {
        val remoteUsers = _remoteDataSource.readAllUser()
        _localDataSource.save(remoteUsers)
    }

    override suspend fun readAllUser(): List<User> {
        return _remoteDataSource.readAllUser()
    }

    override suspend fun readUserById(id: Long): User? {
        return _remoteDataSource.readUserById(id)
    }
}