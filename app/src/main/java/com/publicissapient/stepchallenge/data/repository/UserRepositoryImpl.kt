package com.publicissapient.stepchallenge.data.repository

import com.publicissapient.stepchallenge.BuildConfig
import com.publicissapient.stepchallenge.data.source.network.apis.GoogleSheetsApiService
import com.publicissapient.stepchallenge.data.source.network.dto.SheetsUpdateRequest
import com.publicissapient.stepchallenge.domain.entity.User
import com.publicissapient.stepchallenge.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val apiService: GoogleSheetsApiService
) : UserRepository {

    override fun addUser(user: User): Flow<Boolean> {
        return postUser(user)
    }

    override fun updateUser(id: String, updatedUser: User): Flow<Boolean> {
        return putUser(user = updatedUser)
    }

    override fun getUsers(): Flow<List<User>> {
        return fetchUsers()
    }

    private fun fetchUsers(): Flow<List<User>> {
        val apiKey: String = BuildConfig.GOOGLE_SHEET_API_KEY
        val sheetId: String = BuildConfig.GOOGLE_SHEET_ID

        return flow<List<User>> {
//            apiService.getSheetData(
//                spreadsheetId = sheetId,
//                sheetRange = "A1:B1",
//                apiKey = apiKey
//            )

            val users = listOf(
                User("1", "John Doe", 25, "john.doe@example.com", false),
                User("2", "Jane Smith", 30, "jane.smith@example.com", true),
                User("3", "Bob Johnson", 35, "bob.johnson@example.com", false)
            )
            emit(users)
        }
    }

    private fun postUser(user: User): Flow<Boolean> {
        val apiKey: String = BuildConfig.GOOGLE_SHEET_API_KEY
        val sheetId: String = BuildConfig.GOOGLE_SHEET_ID
        val sheetRange = "A1:B1"
        return flow<Boolean> {
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
        return flow<Boolean> {
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