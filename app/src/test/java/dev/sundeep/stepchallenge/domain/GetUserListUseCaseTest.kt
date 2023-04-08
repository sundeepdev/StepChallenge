package dev.sundeep.stepchallenge.domain

import dev.sundeep.stepchallenge.domain.repository.UserRepository
import dev.sundeep.stepchallenge.domain.usecase.GetUserListUseCase
import dev.sundeep.stepchallenge.util.getUserList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUserListUseCaseTest {

    private lateinit var useCase: GetUserListUseCase
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = mockk()
    }

    @Test
    fun `test fetchLatestUsers success`() {
        val users = getUserList()
        coEvery { userRepository.getUsers() } returns flowOf(Result.success(users))

        useCase = GetUserListUseCase(userRepository)
        runTest {
            useCase()
        }

        coVerify { userRepository.getUsers() }

        confirmVerified( userRepository )
    }
}