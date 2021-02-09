package com.example.itunesapp.di.retrofitdi

import com.example.itunesapp.retrofit.ItunesApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://itunes.apple.com/")
    }

    @Provides
    @Singleton
    fun provideItunesApiService(gson: Gson, retrofit: Retrofit.Builder): ItunesApiService {
        return retrofit.addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(ItunesApiService::class.java)
    }
}