package com.alanturing.lookeo_movil.core.di

import com.alanturing.lookeo_movil.features.auth.data.local.datasource.TokenLocalDataSource
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.TokenLocalDataSourceImpl
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.UserLocalDBDatasource
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.UserLocalDataSource
import com.alanturing.lookeo_movil.features.auth.data.remote.UserRemoteDataSource
import com.alanturing.lookeo_movil.features.auth.data.remote.UserRemoteDataSourceImpl
import com.alanturing.lookeo_movil.features.auth.data.repository.UserRepository
import com.alanturing.lookeo_movil.features.auth.data.repository.UserRepositoryImpl
import com.alanturing.lookeo_movil.features.items.data.local.datasource.ItemLocalDBDatasource
import com.alanturing.lookeo_movil.features.items.data.local.datasource.ItemLocalDataSource
import com.alanturing.lookeo_movil.features.items.data.remote.ItemRemoteDataSource
import com.alanturing.lookeo_movil.features.items.data.remote.ItemRemoteDataSourceImpl
import com.alanturing.lookeo_movil.features.items.data.repository.ItemRepository
import com.alanturing.lookeo_movil.features.items.data.repository.ItemRepositoryImpl
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

    @Binds
    @Singleton
    abstract fun bindsItemLocalDataSource(ds: ItemLocalDBDatasource): ItemLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsItemRemoteDataSource(ds: ItemRemoteDataSourceImpl): ItemRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsItemRepository(ds: ItemRepositoryImpl): ItemRepository
}