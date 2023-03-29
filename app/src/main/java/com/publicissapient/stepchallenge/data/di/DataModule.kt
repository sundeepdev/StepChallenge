package com.publicissapient.stepchallenge.data.di

import com.publicissapient.stepchallenge.data.repository.StepsDataRepositoryImpl
import com.publicissapient.stepchallenge.data.repository.UserRepositoryImpl
import com.publicissapient.stepchallenge.data.source.network.apis.GoogleSheetsApiService
import com.publicissapient.stepchallenge.domain.repository.StepsDataRepository
import com.publicissapient.stepchallenge.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: GoogleSheetsApiService): UserRepository =
        UserRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideStepDataRepository(apiService: GoogleSheetsApiService): StepsDataRepository =
        StepsDataRepositoryImpl(apiService)
}
