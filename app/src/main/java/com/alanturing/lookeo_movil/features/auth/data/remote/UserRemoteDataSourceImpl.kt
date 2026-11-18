package com.alanturing.lookeo_movil.features.auth.data.remote

import com.alanturing.lookeo_movil.features.auth.data.model.User
import kotlinx.coroutines.flow.flow

im
import com.alanturing.lookeo_movil.core.data.remote.LookeoApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRemoteDataSourceImpl
    @Inject constructor(
    private val api: LookeoApi
) {
        val loginState: Flow<User> = flow {

        }
    suspend fun login(email:String, pass: String) {
        api.login(LoginBody(email,pass))


    }
}