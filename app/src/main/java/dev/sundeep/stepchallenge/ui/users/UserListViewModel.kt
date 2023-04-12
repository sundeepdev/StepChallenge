package dev.sundeep.stepchallenge.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sundeep.stepchallenge.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userUseCase: GetUserListUseCase,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    init {
        fetchLatestUsers()
    }

    private val _uiState = MutableStateFlow<UsersListUiState>(UsersListUiState.Loading)
    val uiState: StateFlow<UsersListUiState>
        get() = _uiState.asStateFlow()

    private fun fetchLatestUsers() {
        viewModelScope.launch(dispatcher) {
            userUseCase().collect { result ->
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