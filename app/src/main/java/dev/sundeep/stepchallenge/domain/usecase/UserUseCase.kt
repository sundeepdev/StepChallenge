package dev.sundeep.stepchallenge.domain.usecase

import dev.sundeep.stepchallenge.domain.entity.User
import dev.sundeep.stepchallenge.domain.repository.UserRepository
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

    fun getUsers(): Flow<Result<List<User>>> {
        return userRepository.getUsers()
    }
}