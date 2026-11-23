package com.alanturing.lookeo_movil.features.auth.data.local.datasource

import com.alanturing.lookeo_movil.core.data.local.toEntity
import com.alanturing.lookeo_movil.core.data.local.toModel
import com.alanturing.lookeo_movil.core.data.local.UserDao
import com.alanturing.lookeo_movil.features.auth.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserLocalDBDatasource @Inject constructor(
    private val dao: UserDao
): UserLocalDataSource {

    override fun observeUser(id: Int): Flow<User?> {
        return dao.observeOneById(id).map { entity ->
            entity.toModel()
        }
    }

    override fun observeUser(): Flow<User?> {
        return dao.observeUser().map { entity ->
            entity.toModel()
        }
    }

    override suspend fun saveUser(user: User) {
        val entity = user.toEntity()
        dao.create(entity)
    }
}