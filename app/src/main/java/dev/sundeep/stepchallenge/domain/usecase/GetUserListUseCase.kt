package dev.sundeep.stepchallenge.domain.usecase

import dev.sundeep.stepchallenge.domain.entity.User
import dev.sundeep.stepchallenge.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
    ) {
    operator fun invoke(): Flow<Result<List<User>>> = userRepository.getUsers()
}