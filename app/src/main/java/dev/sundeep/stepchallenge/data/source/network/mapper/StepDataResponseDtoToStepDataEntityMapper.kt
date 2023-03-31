package dev.sundeep.stepchallenge.data.source.network.mapper

import dev.sundeep.stepchallenge.data.source.network.dto.SheetsResponse
import dev.sundeep.stepchallenge.data.source.network.dto.SheetsUpdateResponse
import dev.sundeep.stepchallenge.domain.entity.StepData
import java.util.*

fun SheetsResponse.toStepDataList(): List<StepData> {
    print(this)
    return getStepDataList()
}

fun SheetsUpdateResponse.toStepDataList(): List<StepData> {
    print(this)
    return getStepDataList()
}

private fun getStepDataList(): List<StepData> {
    return listOf(
        StepData(1000, Date()),
        StepData(1000, Date()),
        StepData(1000, Date())
    )
}