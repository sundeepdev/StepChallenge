package com.publicissapient.stepchallenge.domain.repository

import com.publicissapient.stepchallenge.domain.entity.StepData
import kotlinx.coroutines.flow.Flow

interface StepsDataRepository {
    fun getStepsData(forUserWithId: String): Flow<List<StepData>>
}
