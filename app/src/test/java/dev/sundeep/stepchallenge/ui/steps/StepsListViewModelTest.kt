package dev.sundeep.stepchallenge.ui.steps

import dev.sundeep.stepchallenge.domain.usecase.GetStepsDataUseCase
import dev.sundeep.stepchallenge.util.getStepsDataList
import dev.sundeep.stepchallenge.util.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class StepsListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: StepsListViewModel
    private var getStepsDataUseCase: GetStepsDataUseCase = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `GIVEN api is working WHEN view is ready THEN StepsListUiState is Success`() = runTest {
        val st = getStepsDataList()
        coEvery { getStepsDataUseCase() } returns flowOf(Result.success(st))

        viewModel = StepsListViewModel(getStepsDataUseCase)

        viewModel.uiState.test { results ->
            viewModel.onViewReady()

            advanceUntilIdle()
            assertEquals(results.size, 2)
            assert(results[0] is StepsListUiState.Loading)
            assert(results[1] is StepsListUiState.Success)
            assertEquals((results[1] as StepsListUiState.Success).steps.size, 3)
        }

        coVerify { getStepsDataUseCase() }
    }

    @Test
    fun `GIVEN api is not working WHEN view is ready THEN StepsListUiState is Error`() = runTest {
        coEvery { getStepsDataUseCase() } returns flowOf(Result.failure(Throwable("Failure Test")))

        viewModel = StepsListViewModel(getStepsDataUseCase)

        viewModel.uiState.test { results ->
            viewModel.onViewReady()

            advanceUntilIdle()
            assertEquals(results.size, 2)
            assert(results[0] is StepsListUiState.Loading)
            assert(results[1] is StepsListUiState.Error)
        }

        coVerify { getStepsDataUseCase() }
    }
}
