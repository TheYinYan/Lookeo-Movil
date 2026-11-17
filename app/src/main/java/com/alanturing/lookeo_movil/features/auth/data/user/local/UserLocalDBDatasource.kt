package com.alanturing.lookeo_movil.features.auth.data.user.local

import com.alanturing.lookeo_movil.features.auth.data.user.local.model.UserEntity
import com.alanturing.lookeo_movil.features.auth.data.user.local.model.toEntity
import com.alanturing.lookeo_movil.features.auth.data.user.local.model.toModel
import com.alanturing.lookeo_movil.features.auth.data.user.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserLocalDBDatasource @Inject constructor(
    private val dao: UserDao
): UserLocalDataSource {

    override val users: Flow<List<User>>
        = dao.observeAll()
        .map(List<UserEntity>::toModel)

    override fun observeUser(id: Long): Flow<User?> {
        return dao.observeOneById(id).map(UserEntity::toModel)
    }

    override suspend fun save(users: List<User>) {
        val entities = users.toEntity()
        dao.create(entities)
    }
}