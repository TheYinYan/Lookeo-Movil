package com.alanturing.lookeo_movil.features.auth.data.local

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.alanturing.lookeo_movil.features.auth.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(users: List<UserEntity>)

    @Insert
    suspend fun create(user: UserEntity)

    @Query("SELECT * FROM user")
    fun observeAll(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun observeOneById(id: Long): Flow<UserEntity>
}