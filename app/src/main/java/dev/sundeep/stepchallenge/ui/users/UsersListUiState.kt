package dev.sundeep.stepchallenge.ui.users

import dev.sundeep.stepchallenge.domain.entity.User

sealed interface UsersListUiState {
    object Loading: UsersListUiState
    data class Success(val users: List<User>): UsersListUiState
    data class Error(val message: String?): UsersListUiState
}