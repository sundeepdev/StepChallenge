package dev.sundeep.stepchallenge.domain.usecase

import dev.sundeep.stepchallenge.domain.entity.StepData
import dev.sundeep.stepchallenge.domain.repository.StepsDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StepsUseCase @Inject constructor(
    private val stepsDataRepository: StepsDataRepository
) {

    fun getStepsData(): Flow<Result<List<StepData>>> {
        val forUserWithId = "currentUser"
        return stepsDataRepository.getStepsData(forUserWithId)
    }
}
