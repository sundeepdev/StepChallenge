package dev.sundeep.stepchallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface HomeViewUiState {
    object UserListScreen : HomeViewUiState
    object StepsListScreen : HomeViewUiState
    object UserProfileScreen : HomeViewUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow<HomeViewUiState> (HomeViewUiState.UserListScreen)
    val uiState: StateFlow<HomeViewUiState> = _uiState.asStateFlow()

    fun tabClicked(tab: HomeViewTabType) {
        when(tab) {
            HomeViewTabType.USERS_LIST_TAB -> showUserListScreen()
            HomeViewTabType.STEPS_LIST_TAB -> showStepsListScreen()
            HomeViewTabType.PROFILE_TAB -> showUserProfileScreen()
        }
    }

    private fun showUserListScreen() {
        viewModelScope.launch {
            _uiState.emit(HomeViewUiState.UserListScreen)
        }
    }

    private fun showStepsListScreen() {
        viewModelScope.launch {
            _uiState.emit(HomeViewUiState.StepsListScreen)
        }
    }

    private fun showUserProfileScreen() {
        viewModelScope.launch {
            _uiState.emit(HomeViewUiState.UserProfileScreen)
        }
    }
}
