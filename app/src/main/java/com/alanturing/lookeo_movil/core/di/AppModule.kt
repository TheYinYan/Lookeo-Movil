package com.alanturing.lookeo_movil.core.di

import com.alanturing.lookeo_movil.features.auth.data.local.datasource.TokenLocalDataSource
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.TokenLocalDataSourceImpl
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.UserLocalDBDatasource
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.UserLocalDataSource
import com.alanturing.lookeo_movil.features.auth.data.remote.UserRemoteDataSource
import com.alanturing.lookeo_movil.features.auth.data.remote.UserRemoteDataSourceImpl
import com.alanturing.lookeo_movil.features.auth.data.repository.UserRepository
import com.alanturing.lookeo_movil.features.auth.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindsUserRemoteDataSource(ds: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsUserLocalDataSource(ds: UserLocalDBDatasource): UserLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsUserRepository(ds: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindsTokenLocalDataSource(
        ds: TokenLocalDataSourceImpl
    ): TokenLocalDataSource
}