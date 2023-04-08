package dev.sundeep.stepchallenge.domain

import dev.sundeep.stepchallenge.domain.repository.StepsDataRepository
import dev.sundeep.stepchallenge.domain.usecase.GetStepsDataUseCase
import dev.sundeep.stepchallenge.util.getStepsDataList
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
class GetStepsDataUseCaseTest {

    private lateinit var useCase: GetStepsDataUseCase
    private lateinit var stepsDataRepository: StepsDataRepository

    @Before
    fun setUp() {
        stepsDataRepository = mockk()
    }

    @Test
    fun `test GetStepsDataUseCase`() {
        val users = getStepsDataList()
        coEvery { stepsDataRepository.getStepsData() } returns flowOf(Result.success(users))

        useCase = GetStepsDataUseCase(stepsDataRepository)
        runTest {
            useCase()
        }

        coVerify { stepsDataRepository.getStepsData() }

        confirmVerified( stepsDataRepository )
    }
}