package dev.sundeep.stepchallenge.data.source.network.mapper

import dev.sundeep.stepchallenge.domain.entity.User
import javax.inject.Inject

class UserEntityToRequestDataMapper @Inject constructor() {
    operator fun invoke(user: User):List<String> {
        return with(user) {
            listOf(
                name,
                age.toString(),
                email,
                isAdmin.toString()
            )
        }
    }
}