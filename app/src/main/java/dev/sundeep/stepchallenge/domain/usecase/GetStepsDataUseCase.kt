package dev.sundeep.stepchallenge.domain.usecase

import dev.sundeep.stepchallenge.domain.entity.StepData
import dev.sundeep.stepchallenge.domain.repository.StepsDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStepsDataUseCase @Inject constructor(
    private val stepsDataRepository: StepsDataRepository
) {
    operator fun invoke(): Flow<Result<List<StepData>>> {
        return stepsDataRepository.getStepsData()
    }
}
