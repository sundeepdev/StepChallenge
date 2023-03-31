package dev.sundeep.stepchallenge.data.source.network.mapper

import dev.sundeep.stepchallenge.data.source.network.dto.SheetsResponse
import dev.sundeep.stepchallenge.data.source.network.dto.SheetsUpdateResponse
import dev.sundeep.stepchallenge.domain.entity.User

fun SheetsResponse.toUserList(): List<User> {
    print(this)
    return getUserList()
}

fun SheetsUpdateResponse.toUserList(): List<User> {
    print(this)
    return getUserList()
}

private fun getUserList(): List<User> {
    return listOf(
        User("1", "John Doe", 25, "john.doe@example.com", false),
        User("2", "Jane Smith", 30, "jane.smith@example.com", true),
        User("3", "Bob Johnson", 35, "bob.johnson@example.com", false)
    )
}

