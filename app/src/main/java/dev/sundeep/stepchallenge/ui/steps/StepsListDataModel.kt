package dev.sundeep.stepchallenge.ui.steps

import dev.sundeep.stepchallenge.domain.entity.StepData
import dev.sundeep.stepchallenge.domain.util.Utilities

const val DISPLAY_DATE_FORMAT = "dd/MM/yyyy"

data class StepListUiDataModel(val stepCount: Int, val onDate: String) {
    constructor(stepData: StepData): this(
        stepCount = stepData.count,
        onDate = Utilities.formatDate(stepData.date, DISPLAY_DATE_FORMAT)
    )
}