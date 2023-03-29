package com.publicissapient.stepchallenge.domain.usecase

import com.publicissapient.stepchallenge.domain.entity.User
import com.publicissapient.stepchallenge.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
    ) {

    fun addUser(user: User): Flow<Boolean> {
        return userRepository.addUser(user = user)
    }

    fun updateUser(id: String, updatedUser: User): Flow<Boolean> {
        return userRepository.updateUser(id, updatedUser)
    }

    fun getUsers(): Flow<List<User>> {
        return userRepository.getUsers()
    }
}