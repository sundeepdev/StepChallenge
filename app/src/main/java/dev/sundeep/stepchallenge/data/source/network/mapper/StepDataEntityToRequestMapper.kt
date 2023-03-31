package dev.sundeep.stepchallenge.data.source.network.mapper

import dev.sundeep.stepchallenge.domain.entity.StepData
import javax.inject.Inject

class StepDataEntityToRequestMapper @Inject constructor() {
    operator fun invoke(stepData: StepData):List<String> = listOf(
        stepData.count.toString(),
        stepData.date.toString()
    )

    operator fun invoke(stepDataList: List<StepData>): List<List<String>> = stepDataList
                                                                            .map { this.invoke(it) }
}