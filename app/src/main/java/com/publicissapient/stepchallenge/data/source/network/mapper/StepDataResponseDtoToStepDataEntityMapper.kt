package com.publicissapient.stepchallenge.data.source.network.mapper

import com.publicissapient.stepchallenge.data.source.network.dto.SheetsResponse
import com.publicissapient.stepchallenge.data.source.network.dto.SheetsUpdateResponse
import com.publicissapient.stepchallenge.domain.entity.StepData
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