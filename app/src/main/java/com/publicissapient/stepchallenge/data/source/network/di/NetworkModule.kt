package com.publicissapient.stepchallenge.data.source.network.di

import com.publicissapient.stepchallenge.data.source.network.apis.GoogleSheetsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGoogleSheetsApiService(): GoogleSheetsApiService {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://sheets.googleapis.com/v4/spreadsheets/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GoogleSheetsApiService::class.java)
    }
}
