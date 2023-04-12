package dev.sundeep.stepchallenge.data.source.network.mapper

import dev.sundeep.stepchallenge.domain.entity.StepData
import javax.inject.Inject

class StepDataEntityToRequestMapper @Inject constructor() {
    operator fun invoke(stepData: StepData):List<String> {
        return with(stepData) { listOf(count.toString(), date.toString()) }
    }
}