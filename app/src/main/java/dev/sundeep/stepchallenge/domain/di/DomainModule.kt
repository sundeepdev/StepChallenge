package dev.sundeep.stepchallenge.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.sundeep.stepchallenge.domain.repository.StepsDataRepository
import dev.sundeep.stepchallenge.domain.repository.UserRepository
import dev.sundeep.stepchallenge.domain.usecase.GetStepsDataUseCase
import dev.sundeep.stepchallenge.domain.usecase.GetUserListUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun provideUserUseCase(userRepository: UserRepository): GetUserListUseCase {
        return GetUserListUseCase(userRepository)
    }

    @Provides
    fun provideStepsUseCase(stepsDataRepository: StepsDataRepository): GetStepsDataUseCase {
        return GetStepsDataUseCase(stepsDataRepository)
    }

}