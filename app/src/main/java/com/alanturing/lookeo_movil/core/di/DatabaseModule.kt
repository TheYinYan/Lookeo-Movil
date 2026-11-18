package com.alanturing.lookeo_movil.core.di

import android.content.Context
import androidx.room3.Room
import com.alanturing.lookeo_movil.core.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): UserDatabase = Room.databaseBuilder<UserDatabase>(
            context = context,
            name = "user-db"
        ).build()

    @Provides
    fun providesUserDao(db: UserDatabase) = db.userDao()
}