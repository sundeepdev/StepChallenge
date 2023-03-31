package dev.sundeep.stepchallenge.data.source.network.mapper

import dev.sundeep.stepchallenge.domain.entity.User
import javax.inject.Inject

class UserEntityToRequestDataMapper @Inject constructor() {
    operator fun invoke(user: User):List<String> = listOf(
        user.name,
        user.age.toString(),
        user.email,
        user.isAdmin.toString()
    )

    operator fun invoke(userList: List<User>): List<List<String>> = userList.map { this.invoke(it) }
}