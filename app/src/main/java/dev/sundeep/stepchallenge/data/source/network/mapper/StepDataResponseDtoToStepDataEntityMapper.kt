package dev.sundeep.stepchallenge.data.source.network.mapper

import dev.sundeep.stepchallenge.data.source.network.dto.SheetsResponse
import dev.sundeep.stepchallenge.domain.entity.StepData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

const val SERVER_DATE_FORMAT = "dd/MM/yyyy"
class SheetResponseToStepDataMapper @Inject constructor(){
    private val dateFormatter = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.UK)

    private infix fun map(list: List<String>) = StepData(list.first().toInt(), dateFormatter.parse(list.last()) ?: Date())

    private fun getStepDataList(): List<StepData> {
        return listOf(
            StepData(1000, Date()),
            StepData(1000, Date()),
            StepData(1000, Date())
        )
    }

    fun mapToStepDataList(sheetsResponse: SheetsResponse): List<StepData> {
        return try {
                    sheetsResponse.values.map { this map it }
                } catch(exception: Exception) {
                    getStepDataList()
                }
    }
}




