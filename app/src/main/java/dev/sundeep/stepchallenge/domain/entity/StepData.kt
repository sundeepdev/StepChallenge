package dev.sundeep.stepchallenge.domain.entity

import dev.sundeep.stepchallenge.domain.util.Utilities
import java.util.Date

const val DISPLAY_DATE_FORMAT = "dd/MM/yyyy"

data class StepData(val count:Int, val date:Date) {

    fun stepDataDisplayDate(): String {
        return Utilities.formatDate(date, DISPLAY_DATE_FORMAT)
    }
}
