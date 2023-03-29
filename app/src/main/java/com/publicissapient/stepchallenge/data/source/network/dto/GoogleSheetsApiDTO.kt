package com.publicissapient.stepchallenge.data.source.network.dto

data class SheetsResponse(
    val range: String,
    val values: List<List<String>>
)

data class SheetsUpdateRequest(
    val range: String,
    val values: List<List<String>>
)

data class SheetsUpdateResponse(
    val spreadsheetId: String,
    val updatedRange: String,
    val updatedRows: Int
)
