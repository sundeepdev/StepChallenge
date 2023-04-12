package dev.sundeep.stepchallenge.util

import dev.sundeep.stepchallenge.data.source.network.dto.SheetsResponse
import dev.sundeep.stepchallenge.domain.entity.StepData
import dev.sundeep.stepchallenge.domain.entity.User
import java.util.*

fun getStepsDataList(): List<StepData> {
    return listOf(
        StepData(1000, Date()),
        StepData(1000, Date()),
        StepData(1000, Date())
    )
}

fun getUserList(): List<User> {
    return listOf(
        User("1", "John Doe", 25, "john.doe@example.com", false),
        User("2", "Jane Smith", 30, "jane.smith@example.com", true),
        User("3", "Bob Johnson", 35, "bob.johnson@example.com", false)
    )
}

fun getSheetResponseForUserList(): SheetsResponse {
    return SheetsResponse(
        range = "A1:Z10",
        values = listOf(
            listOf("1", "John Doe", "25", "john.doe@example.com", "false"),
            listOf("2", "Jane Smith", "30", "jane.smith@example.com", "true"),
            listOf("3", "Bob Johnson", "35", "bob.johnson@example.com", "false")
        )
    )
}

fun getSheetResponseForStepData(): SheetsResponse {
    return SheetsResponse(
        range = "A1:Z10",
        values = listOf(
            listOf("1000", "12/04/2023"),
            listOf("2000", "11/04/2023"),
            listOf("3000", "10/04/2023"),
            listOf("3000", "10/04/2023")
        )
    )
}