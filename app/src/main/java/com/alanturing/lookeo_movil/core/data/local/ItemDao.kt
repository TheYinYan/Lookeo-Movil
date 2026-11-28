package com.alanturing.lookeo_movil.core.data.local

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ItemEntity>)

    @Query("SELECT * FROM item WHERE userId = :userId ORDER BY createdAt DESC")
    fun observeByUser(userId: Int): Flow<List<ItemEntity>>

    @Query("SELECT * FROM item WHERE id = :id")
    fun observeById(id: Int): Flow<ItemEntity?>

	@Query("SELECT * FROM item WHERE syncStatus != 'SYNCED'")
	suspend fun getPending(): List<ItemEntity>

    @Query("DELETE FROM item WHERE userId = :userId")
    suspend fun clearByUser(userId: Int)

    @Query("DELETE FROM item WHERE id = :id")
    suspend fun deleteById(id: Int)

	@Query("DELETE FROM item WHERE userId = :userId AND syncStatus = 'SYNCED'")
	suspend fun clearSyncedByUser(userId: Int)
}