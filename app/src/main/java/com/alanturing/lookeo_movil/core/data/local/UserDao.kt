package com.alanturing.lookeo_movil.core.data.local

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(user: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    fun observeUser(): Flow<UserEntity?>

    @Query("SELECT * FROM user WHERE id = :id")
    fun observeOneById(id: Int): Flow<UserEntity?>
}