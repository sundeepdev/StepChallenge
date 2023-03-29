package dev.sundeep.stepchallenge.data.repository

import dev.sundeep.stepchallenge.BuildConfig
import dev.sundeep.stepchallenge.data.source.network.apis.GoogleSheetsApiService
import dev.sundeep.stepchallenge.data.source.network.dto.SheetsUpdateRequest
import dev.sundeep.stepchallenge.domain.entity.User
import dev.sundeep.stepchallenge.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import dev.sundeep.stepchallenge.data.source.network.mapper.toUserList


class UserRepositoryImpl @Inject constructor(
    private val apiService: GoogleSheetsApiService
) : UserRepository {

    override fun addUser(user: User): Flow<Boolean> {
        return postUser(user)
    }

    override fun updateUser(id: String, updatedUser: User): Flow<Boolean> {
        return putUser(user = updatedUser)
    }

    override fun getUsers(): Flow<Result<List<User>>> {
        return fetchUsers()
    }

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
                delay(1000)
                emit(Result.success(users))
            } catch (error: Exception) {
                emit(Result.failure(error))
            }
        }
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
                data = SheetsUpdateRequest(range = sheetRange, values = listOf(listOf(user.id, user.name, user.email, user.age.toString())))
            )
            emit(response.updatedRows > 0)
        }
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
                data = SheetsUpdateRequest(range = sheetRange, values = listOf(listOf(user.id, user.name, user.email, user.age.toString())))
            )
            emit(response.updatedRows > 0)
        }
    }
}