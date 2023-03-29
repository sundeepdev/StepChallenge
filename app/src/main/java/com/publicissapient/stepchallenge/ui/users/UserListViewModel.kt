package com.publicissapient.stepchallenge.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.publicissapient.stepchallenge.domain.entity.User
import com.publicissapient.stepchallenge.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UsersListUiState {
    object Loading: UsersListUiState
    data class Success(val users: List<User>): UsersListUiState
    data class Error(val message: String?): UsersListUiState
}

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    init {
        fetchLatestUsers()
    }

    private val _uiState = MutableStateFlow<UsersListUiState>(UsersListUiState.Loading)
    val uiState: StateFlow<UsersListUiState>
        get() = _uiState

    private fun fetchLatestUsers() {
        viewModelScope.launch {
            userUseCase.getUsers().collect { result ->
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
    }
}