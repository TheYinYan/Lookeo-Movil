package com.alanturing.lookeo_movil.features.auth.data.local.datasource

import com.alanturing.lookeo_movil.core.data.local.UserEntity
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

    override val users: Flow<List<User>>
        = dao.observeAll()
        .map(List<UserEntity>::toModel)

    override fun observeUser(id: Long): Flow<User?> {
        return dao.observeOneById(id).map(UserEntity::toModel)
    }

    override suspend fun saveUser(user: User) {
        val entity = user.toEntity()
        dao.create(entity)
    }

    override suspend fun saveUsers(users: List<User>) {
        val entities = users.toEntity()
        dao.create(entities)
    }
}