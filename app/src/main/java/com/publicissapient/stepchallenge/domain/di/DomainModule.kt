package com.publicissapient.stepchallenge.domain.di

import com.publicissapient.stepchallenge.domain.repository.StepsDataRepository
import com.publicissapient.stepchallenge.domain.repository.UserRepository
import com.publicissapient.stepchallenge.domain.usecase.StepsUseCase
import com.publicissapient.stepchallenge.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideUserUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideStepsUseCase(stepsDataRepository: StepsDataRepository): StepsUseCase {
        return StepsUseCase(stepsDataRepository)
    }

}