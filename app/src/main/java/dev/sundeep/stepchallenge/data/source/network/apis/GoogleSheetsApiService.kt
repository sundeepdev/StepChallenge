package dev.sundeep.stepchallenge.data.source.network.apis

import dev.sundeep.stepchallenge.data.source.network.dto.SheetsResponse
import dev.sundeep.stepchallenge.data.source.network.dto.SheetsUpdateRequest
import dev.sundeep.stepchallenge.data.source.network.dto.SheetsUpdateResponse
import retrofit2.http.*

interface GoogleSheetsApiService {
    @GET("{spreadsheetId}/values/{sheetRange}")
    suspend fun getSheetData(
        @Path("spreadsheetId") spreadsheetId: String,
        @Path("sheetRange") sheetRange: String,
        @Query("key") apiKey: String
    ): SheetsResponse

    @PUT("{spreadsheetId}/values/{sheetRange}?valueInputOption=RAW")
    suspend fun writeToSheet(
        @Path("spreadsheetId") spreadsheetId: String,
        @Path("sheetRange") sheetRange: String,
        @Query("key") apiKey: String,
        @Body data: SheetsUpdateRequest
    ): SheetsUpdateResponse
}
