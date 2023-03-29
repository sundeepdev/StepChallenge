package com.publicissapient.stepchallenge.data.repository

import com.publicissapient.stepchallenge.BuildConfig
import com.publicissapient.stepchallenge.data.source.network.apis.GoogleSheetsApiService
import com.publicissapient.stepchallenge.data.source.network.dto.SheetsUpdateRequest
import com.publicissapient.stepchallenge.data.source.network.mapper.toStepDataList
import com.publicissapient.stepchallenge.domain.entity.StepData
import com.publicissapient.stepchallenge.domain.repository.StepsDataRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class StepsDataRepositoryImpl @Inject constructor(
    private val apiService: GoogleSheetsApiService
) : StepsDataRepository {

    private fun postSteps(stepData: StepData): Flow<Boolean> {
        val apiKey: String = BuildConfig.GOOGLE_SHEET_API_KEY
        val sheetId: String = BuildConfig.GOOGLE_SHEET_ID
        val sheetRange = "A1:B1"
        return flow<Boolean> {
            val response = apiService.writeToSheet(
                spreadsheetId = sheetId,
                sheetRange = sheetRange,
                apiKey = apiKey,
                data = SheetsUpdateRequest(range = sheetRange, values = listOf(listOf(stepData.count.toString(), stepData.stepDataDisplayDate())))
            )
            emit(response.updatedRows > 0)
        }
    }

    override fun getStepsData(forUserWithId: String): Flow<Result<List<StepData>>> {
        val apiKey: String = BuildConfig.GOOGLE_SHEET_API_KEY
        val sheetId: String = BuildConfig.GOOGLE_SHEET_ID

        return flow<Result<List<StepData>>> {
            // ToDo: Need to update code to send and receive request.
            try {
                val steps = apiService.getSheetData(
                    spreadsheetId = sheetId,
                    sheetRange = "A1:B1",
                    apiKey = apiKey
                ).toStepDataList()
                delay(1000)

                emit(Result.success(steps))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }

        }
    }
}