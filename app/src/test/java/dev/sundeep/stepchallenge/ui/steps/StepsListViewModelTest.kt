package dev.sundeep.stepchallenge.ui.steps

import androidx.lifecycle.viewModelScope
import dev.sundeep.stepchallenge.domain.usecase.GetStepsDataUseCase
import dev.sundeep.stepchallenge.util.getStepsDataList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class StepsListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: StepsListViewModel
    private lateinit var getStepsDataUseCase: GetStepsDataUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getStepsDataUseCase = mockk()
    }

    @Test
    fun `test GetStepsDataUseCase success`() = runTest {
        val st = getStepsDataList()
        coEvery { getStepsDataUseCase() } returns flowOf(Result.success(st))

        viewModel = StepsListViewModel(getStepsDataUseCase)

        var uiState = viewModel.uiState.value
        assert(uiState is StepsListUiState.Loading)

        viewModel.viewModelScope.launch {
            viewModel.uiState.collectLatest {
                uiState = it
            }
        }
        delay(100)

        assert(uiState is StepsListUiState.Success)
        assertEquals((uiState as StepsListUiState.Success).steps.size, 3)
        coVerify { getStepsDataUseCase() }
    }

    @Test
    fun `test GetStepsDataUseCase failure`() = runTest {
        coEvery { getStepsDataUseCase() } returns flowOf(Result.failure(Throwable("Failure Test")))

        viewModel = StepsListViewModel(getStepsDataUseCase)

        var uiState = viewModel.uiState.value
        assert(uiState is StepsListUiState.Loading)

        viewModel.viewModelScope.launch {
            viewModel.uiState.collectLatest {
                uiState = it
            }
        }
        delay(100)

        assert(uiState is StepsListUiState.Error)
        coVerify { getStepsDataUseCase() }
    }
}
