package dev.sundeep.stepchallenge.domain.di

import dev.sundeep.stepchallenge.domain.repository.StepsDataRepository
import dev.sundeep.stepchallenge.domain.repository.UserRepository
import dev.sundeep.stepchallenge.domain.usecase.StepsUseCase
import dev.sundeep.stepchallenge.domain.usecase.UserUseCase
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