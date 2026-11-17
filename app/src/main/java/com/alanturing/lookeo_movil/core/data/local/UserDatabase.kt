package com.alanturing.lookeo_movil.core.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.alanturing.lookeo_movil.features.auth.data.user.local.UserDao
import com.alanturing.lookeo_movil.features.auth.data.user.local.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase(): RoomDatabase() {

    abstract fun userDao(): UserDao
}