package com.publicissapient.stepchallenge.domain.repository

import com.publicissapient.stepchallenge.domain.entity.User
import kotlinx.coroutines.flow.Flow

// Define an interface for Mentor repository
interface UserRepository {
    fun addUser(user: User): Flow<Boolean>
    fun updateUser(id: String, updatedUser: User): Flow<Boolean>
    fun getUsers(): Flow<List<User>>
}