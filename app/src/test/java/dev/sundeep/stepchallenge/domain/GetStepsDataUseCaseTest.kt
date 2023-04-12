package dev.sundeep.stepchallenge.domain

import dev.sundeep.stepchallenge.domain.repository.StepsDataRepository
import dev.sundeep.stepchallenge.domain.usecase.GetStepsDataUseCase
import dev.sundeep.stepchallenge.util.BaseCoroutineTests
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
class GetStepsDataUseCaseTest: BaseCoroutineTests() {
    private lateinit var useCase: GetStepsDataUseCase
    private val stepsDataRepository: StepsDataRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetStepsDataUseCase(stepsDataRepository)
    }

    @Test
    fun `GIVEN step data repository WHEN GetStepDataUseCase is invoked THEN the list of StepsData is returned in case of success`()
        = runTest(coroutinesTestRule.dispatcher) {

            val stepsDataList = getStepsDataList()
            coEvery { stepsDataRepository.getStepsData() } returns flowOf(Result.success(stepsDataList))

            useCase()

            coVerify { stepsDataRepository.getStepsData() }

            confirmVerified( stepsDataRepository )
        }
}