package dev.sundeep.stepchallenge.util

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