package com.alanturing.lookeo_movil.core.data.local

import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [UserEntity::class, ItemEntity::class],
    version = 2,
)
@ColumnTypeConverters(DateConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun itemDao(): ItemDao
}