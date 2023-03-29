package com.publicissapient.stepchallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.publicissapient.stepchallenge.ui.steps.StepsListViewModel
import com.publicissapient.stepchallenge.ui.users.UserListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



sealed interface HomeViewUiState {
    data class UserListScreen(val userListViewModel: UserListViewModel) : HomeViewUiState
    data class StepsListScreen(val stepsListViewModel: StepsListViewModel) : HomeViewUiState
    data class UserProfileScreen(val userId: String) : HomeViewUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userListViewModel: UserListViewModel,
    private val stepsListViewModel: StepsListViewModel
) : ViewModel() {

    private val userListScreenTitle = "User"
    private val _uiState = MutableStateFlow<HomeViewUiState> (HomeViewUiState.UserListScreen(userListViewModel))
    val uiState: StateFlow<HomeViewUiState> = _uiState.asStateFlow()

    fun tabClicked(tab: HomeViewTabType) {
        when(tab) {
            HomeViewTabType.USERS_LIST_TAB -> showUserListScreen()
            HomeViewTabType.STEPS_LIST_TAB -> showStepsListScreen()
            HomeViewTabType.PROFILE_TAB -> showUserProfileScreen("1234")
        }
    }

    private fun showUserListScreen() {
        viewModelScope.launch {
            _uiState.emit(HomeViewUiState.UserListScreen(userListViewModel))
        }
    }

    private fun showStepsListScreen() {
        viewModelScope.launch {
            _uiState.emit(HomeViewUiState.StepsListScreen(stepsListViewModel))
        }
    }

    private fun showUserProfileScreen(userId: String) {
        viewModelScope.launch {
            _uiState.emit(HomeViewUiState.UserProfileScreen(userId = "12345"))
        }
    }
}
