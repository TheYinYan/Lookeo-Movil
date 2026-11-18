package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.model.User
import kotlinx.coroutines.flow.flow

import com.alanturing.lookeo_movil.core.data.remote.LookeoApi
import kotlinx.coroutines.flow.Flow
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
}