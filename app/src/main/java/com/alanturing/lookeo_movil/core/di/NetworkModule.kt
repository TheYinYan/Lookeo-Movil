package com.alanturing.lookeo_movil.core.di

import com.alanturing.lookeo_movil.core.data.remote.AuthInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import com.alanturing.lookeo_movil.core.data.remote.LookeoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(
        authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(
        okHttpClient: OkHttpClient,
    ): LookeoApi {
        val client = Retrofit.Builder()
            .baseUrl("https://server-lookeo.vercel.app/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return client.create<LookeoApi>()
    }
}