package com.publicissapient.stepchallenge.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.publicissapient.stepchallenge.domain.entity.User
import com.publicissapient.stepchallenge.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

sealed interface UsersListUiState {
    object Loading: UsersListUiState
    data class Success(val users: List<User>): UsersListUiState
}

class UserListViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    private val users: Flow<List<User>>

    = userUseCase.getUsers()

    val uiState: StateFlow<UsersListUiState> = users
        .map {
            UsersListUiState.Success(it)
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = UsersListUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000L)
        )
}