package com.publicissapient.stepchallenge.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
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

interface HomeViewTabData {
    val title: String
    val icon: ImageVector
}

enum class HomeViewTab(val tabData: HomeViewTabData) {
    USERS_LIST_TAB(tabData = object: HomeViewTabData {
        override val title = "Users"
        override val icon = Icons.Default.Home
    }),
    STEPS_LIST_TAB(tabData = object: HomeViewTabData {
        override val title = "Steps"
        override val icon = Icons.Default.ThumbUp
    }),
    PROFILE_TAB(tabData = object: HomeViewTabData {
        override val title = "Profile"
        override val icon = Icons.Default.Person
    });
}

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

    fun tabClicked(tab: HomeViewTab) {
        when(tab) {
            HomeViewTab.USERS_LIST_TAB -> showUserListScreen()
            HomeViewTab.STEPS_LIST_TAB -> showStepsListScreen()
            HomeViewTab.PROFILE_TAB -> showUserProfileScreen("1234")
        }
    }

    fun showUserListScreen() {
        viewModelScope.launch {
            _uiState.emit(HomeViewUiState.UserListScreen(userListViewModel))
        }
    }

    fun showStepsListScreen() {
        viewModelScope.launch {
            _uiState.emit(HomeViewUiState.StepsListScreen(stepsListViewModel))
        }
    }

    fun showUserProfileScreen(userId: String) {
        viewModelScope.launch {
            _uiState.emit(HomeViewUiState.UserProfileScreen(userId = "12345"))
        }
    }
}
