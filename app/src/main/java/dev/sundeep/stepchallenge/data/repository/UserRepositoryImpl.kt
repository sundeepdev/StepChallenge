package dev.sundeep.stepchallenge.data.repository

import dev.sundeep.stepchallenge.BuildConfig
import dev.sundeep.stepchallenge.data.source.network.apis.GoogleSheetsApiService
import dev.sundeep.stepchallenge.data.source.network.dto.SheetsUpdateRequest
import dev.sundeep.stepchallenge.data.source.network.mapper.UserEntityToRequestDataMapper
import dev.sundeep.stepchallenge.data.source.network.mapper.toUserList
import dev.sundeep.stepchallenge.di.IoDispatcher
import dev.sundeep.stepchallenge.domain.entity.User
import dev.sundeep.stepchallenge.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: GoogleSheetsApiService,
    private val userEntityToRequestDataMapper: UserEntityToRequestDataMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override fun addUser(user: User): Flow<Boolean> = postUser(user)

    override fun updateUser(id: String, updatedUser: User): Flow<Boolean>
        = putUser(user = updatedUser)

    override fun getUsers(): Flow<Result<List<User>>> = fetchUsers()

    private fun fetchUsers(): Flow<Result<List<User>>> {
        val apiKey: String = BuildConfig.GOOGLE_SHEET_API_KEY
        val sheetId: String = BuildConfig.GOOGLE_SHEET_ID

        return flow {
            try {
                val users = apiService.getSheetData(
                    spreadsheetId = sheetId,
                    sheetRange = "A1:B1",
                    apiKey = apiKey
                ).toUserList()
                emit(Result.success(users))
            } catch (error: Exception) {
                emit(Result.failure(error))
            }
        }.flowOn(dispatcher)
    }

    private fun postUser(user: User): Flow<Boolean> {
        val apiKey: String = BuildConfig.GOOGLE_SHEET_API_KEY
        val sheetId: String = BuildConfig.GOOGLE_SHEET_ID
        val sheetRange = "A1:B1"
        return flow {
            val response = apiService.writeToSheet(
                spreadsheetId = sheetId,
                sheetRange = sheetRange,
                apiKey = apiKey,
                data = SheetsUpdateRequest(range = sheetRange, values = listOf(userEntityToRequestDataMapper(user)))
            )
            emit(response.updatedRows > 0)
        }.flowOn(dispatcher)
    }

    private fun putUser(user: User): Flow<Boolean> {
        val apiKey: String = BuildConfig.GOOGLE_SHEET_API_KEY
        val sheetId: String = BuildConfig.GOOGLE_SHEET_ID
        val sheetRange = "A1:B1"
        return flow {
            val response = apiService.writeToSheet(
                spreadsheetId = sheetId,
                sheetRange = sheetRange,
                apiKey = apiKey,
                data = SheetsUpdateRequest(range = sheetRange, values = listOf(userEntityToRequestDataMapper(user)))
            )
            emit(response.updatedRows > 0)
        }.flowOn(dispatcher)
    }
}