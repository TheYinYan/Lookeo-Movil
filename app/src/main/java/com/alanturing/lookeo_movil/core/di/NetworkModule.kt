package com.alanturing.lookeo_movil.core.di

import com.alanturing.lookeo_movil.core.data.remote.LookeoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesApi(): LookeoApi {
        val client = Retrofit.Builder()
            .baseUrl("LA QUE DEFINAIS")
            .build()
        return client.create<LookeoApi>()
    }
}