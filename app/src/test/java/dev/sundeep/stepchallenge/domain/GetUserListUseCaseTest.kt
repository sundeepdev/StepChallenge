package dev.sundeep.stepchallenge.domain

import dev.sundeep.stepchallenge.domain.repository.UserRepository
import dev.sundeep.stepchallenge.domain.usecase.GetUserListUseCase
import dev.sundeep.stepchallenge.util.BaseCoroutineTests
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
class GetUserListUseCaseTest:BaseCoroutineTests() {

    private lateinit var useCase: GetUserListUseCase
    private val userRepository: UserRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetUserListUseCase(userRepository)
    }

    @Test
    fun `GIVEN user repository WHEN GetUserListUseCase is invoked THEN the getUsers function is called`()
        = runTest(coroutinesTestRule.dispatcher) {
            val users = getUserList()
            coEvery { userRepository.getUsers() } returns flowOf(Result.success(users))

            useCase()

            coVerify { userRepository.getUsers() }

            confirmVerified( userRepository )
        }
}