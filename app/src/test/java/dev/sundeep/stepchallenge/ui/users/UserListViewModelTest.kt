package dev.sundeep.stepchallenge.ui.users

import androidx.lifecycle.viewModelScope
import dev.sundeep.stepchallenge.domain.entity.User
import dev.sundeep.stepchallenge.domain.usecase.GetUserListUseCase
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
class UserListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: UserListViewModel
    private lateinit var getUserListUseCase: GetUserListUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getUserListUseCase = mockk()
    }

    @Test
    fun `test fetchLatestUsers success`() = runTest {
        val users = getUserList()
        coEvery { getUserListUseCase() } returns flowOf(Result.success(users))

        viewModel = UserListViewModel(getUserListUseCase)

        var uiState = viewModel.uiState.value
        assert(uiState is UsersListUiState.Loading)

        viewModel.viewModelScope.launch {
            viewModel.uiState.collectLatest {
                uiState = it
            }
        }
        delay(100)

        assert(uiState is UsersListUiState.Success)
        assertEquals((uiState as UsersListUiState.Success).users.size, 3)
        coVerify { getUserListUseCase() }
    }

    @Test
    fun `test fetchLatestUsers failure`() = runTest {
        coEvery { getUserListUseCase() } returns flowOf(Result.failure(Throwable("Failure Test")))

        viewModel = UserListViewModel(getUserListUseCase)

        var uiState = viewModel.uiState.value
        assert(uiState is UsersListUiState.Loading)

        viewModel.viewModelScope.launch {
            viewModel.uiState.collectLatest {
                uiState = it
            }
        }
        delay(100)

        assert(uiState is UsersListUiState.Error)
        coVerify { getUserListUseCase() }
    }

    private fun getUserList(): List<User> {
        return listOf(
            User("1", "John Doe", 25, "john.doe@example.com", false),
            User("2", "Jane Smith", 30, "jane.smith@example.com", true),
            User("3", "Bob Johnson", 35, "bob.johnson@example.com", false)
        )
    }
}
