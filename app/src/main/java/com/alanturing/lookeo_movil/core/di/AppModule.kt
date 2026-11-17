package com.alanturing.lookeo_movil.core.di

import com.alanturing.lookeo_movil.features.auth.data.user.local.UserLocalDBDatasource
import com.alanturing.lookeo_movil.features.auth.data.user.local.UserLocalDataSource
import dagger.Binds
import javax.inject.Singleton

interface AppModule {
    @Binds
    @Singleton
    fun bindsUserRemoteDataSource(ds: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    fun bindsUserLocalDataSource(ds: UserLocalDBDatasource): UserLocalDataSource

    @Binds
    @Singleton
    fun bindsUserRepository(ds: UserRepositoryImpl): UserRepository