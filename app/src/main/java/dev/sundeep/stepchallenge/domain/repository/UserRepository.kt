package dev.sundeep.stepchallenge.domain.repository

import dev.sundeep.stepchallenge.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun addUser(user: User): Flow<Boolean>
    fun updateUser(id: String, updatedUser: User): Flow<Boolean>
    fun getUsers(): Flow<Result<List<User>>>
}