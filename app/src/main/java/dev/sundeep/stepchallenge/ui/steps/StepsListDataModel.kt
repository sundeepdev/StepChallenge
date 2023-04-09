package dev.sundeep.stepchallenge.ui.steps

import dev.sundeep.stepchallenge.domain.entity.StepData
import java.text.SimpleDateFormat
import java.util.*

const val DISPLAY_DATE_FORMAT = "dd/MM/yyyy"

data class StepListUiDataModel(val stepCount: Int, val onDate: String) {
    constructor(stepData: StepData): this(
        stepCount = stepData.count,
        onDate = SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.UK).format(stepData.date)
    )
}