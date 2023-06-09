package dev.sundeep.stepchallenge.domain.repository

import dev.sundeep.stepchallenge.domain.entity.StepData
import kotlinx.coroutines.flow.Flow

interface StepsDataRepository {
    fun getStepsData(): Flow<Result<List<StepData>>>
}
