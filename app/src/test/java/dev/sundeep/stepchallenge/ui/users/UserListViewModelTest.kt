package dev.sundeep.stepchallenge.ui.users

import dev.sundeep.stepchallenge.domain.entity.User
import dev.sundeep.stepchallenge.domain.usecase.GetUserListUseCase
import dev.sundeep.stepchallenge.util.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
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
    fun `GIVEN api is working WHEN view is ready THEN UsersListUiState is Success`() = runTest {
        val users = getUserList()
        coEvery { getUserListUseCase() } returns flowOf(Result.success(users))

        viewModel = UserListViewModel(getUserListUseCase)

        viewModel.uiState.test {
            viewModel.onViewReady()

            advanceUntilIdle()
            assert(it[0] is UsersListUiState.Loading)
            assert(it[1] is UsersListUiState.Success)
            assertEquals((it[1] as UsersListUiState.Success).users.size, 3)
        }

        coVerify { getUserListUseCase() }
    }

    @Test
    fun `GIVEN api is not working WHEN view is ready THEN UsersListUiState is Error`() = runTest {
        coEvery { getUserListUseCase() } returns flowOf(Result.failure(Throwable("Failure Test")))

        viewModel = UserListViewModel(getUserListUseCase)

        viewModel.uiState.test {
            viewModel.onViewReady()

            advanceUntilIdle()
            assert(it[0] is UsersListUiState.Loading)
            assert(it[1] is UsersListUiState.Error)
        }

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
