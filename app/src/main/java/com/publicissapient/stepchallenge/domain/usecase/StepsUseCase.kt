package com.publicissapient.stepchallenge.domain.usecase

import com.publicissapient.stepchallenge.domain.entity.StepData
import com.publicissapient.stepchallenge.domain.repository.StepsDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StepsUseCase @Inject constructor(
    private val stepsDataRepository: StepsDataRepository
) {

    fun getStepsData(forUserWithId: String): Flow<List<StepData>> {
        return stepsDataRepository.getStepsData(forUserWithId)
    }
}
