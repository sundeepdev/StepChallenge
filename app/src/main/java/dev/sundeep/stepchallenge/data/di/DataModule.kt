package dev.sundeep.stepchallenge.data.di

import dev.sundeep.stepchallenge.data.repository.StepsDataRepositoryImpl
import dev.sundeep.stepchallenge.data.repository.UserRepositoryImpl
import dev.sundeep.stepchallenge.data.source.network.apis.GoogleSheetsApiService
import dev.sundeep.stepchallenge.domain.repository.StepsDataRepository
import dev.sundeep.stepchallenge.domain.repository.UserRepository
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
