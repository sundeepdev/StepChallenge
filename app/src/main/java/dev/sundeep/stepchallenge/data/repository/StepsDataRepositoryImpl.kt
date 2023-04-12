package dev.sundeep.stepchallenge.data.repository

import dev.sundeep.stepchallenge.BuildConfig
import dev.sundeep.stepchallenge.data.source.network.apis.GoogleSheetsApiService
import dev.sundeep.stepchallenge.data.source.network.mapper.SheetResponseToStepDataMapper
import dev.sundeep.stepchallenge.data.source.network.mapper.StepDataEntityToRequestMapper
import dev.sundeep.stepchallenge.di.IoDispatcher
import dev.sundeep.stepchallenge.domain.entity.StepData
import dev.sundeep.stepchallenge.domain.repository.StepsDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class StepsDataRepositoryImpl @Inject constructor(
    private val apiService: GoogleSheetsApiService,
    private val stepDataEntityToRequestMapper: StepDataEntityToRequestMapper,
    private val sheetResponseToStepDataMapper: SheetResponseToStepDataMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : StepsDataRepository {

    override fun getStepsData(): Flow<Result<List<StepData>>> {
        val apiKey: String = BuildConfig.GOOGLE_SHEET_API_KEY
        val sheetId: String = BuildConfig.GOOGLE_SHEET_ID

        return flow {
            try {
                val response = apiService.getSheetData(
                    spreadsheetId = sheetId,
                    sheetRange = "A1:B1",
                    apiKey = apiKey
                )
                val steps = sheetResponseToStepDataMapper.mapToStepDataList(response)
                emit(Result.success(steps))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(dispatcher)
    }
}