package dev.sundeep.stepchallenge.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sundeep.stepchallenge.data.repository.StepsDataRepositoryImpl
import dev.sundeep.stepchallenge.data.repository.UserRepositoryImpl
import dev.sundeep.stepchallenge.domain.repository.StepsDataRepository
import dev.sundeep.stepchallenge.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindStepDataRepository(stepsDataRepositoryImpl: StepsDataRepositoryImpl): StepsDataRepository
}
