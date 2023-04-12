package dev.sundeep.stepchallenge.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sundeep.stepchallenge.domain.entity.User
import dev.sundeep.stepchallenge.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userUseCase: GetUserListUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<UsersListUiState>(UsersListUiState.Loading)
    val uiState: StateFlow<UsersListUiState>
        get() = _uiState.asStateFlow()

    fun onViewReady() {
        fetchLatestUsers()
    }

    private fun fetchLatestUsers() {
        viewModelScope.launch {
            userUseCase().collect { result ->
                handleResult(result = result)
            }
        }
    }

    private fun handleResult(result: Result<List<User>>) {
        result.fold(
            onSuccess = {
                _uiState.value = UsersListUiState.Success(it)
            },
            onFailure = {
                _uiState.value = UsersListUiState.Error(it.message)
            }
        )
    }
}