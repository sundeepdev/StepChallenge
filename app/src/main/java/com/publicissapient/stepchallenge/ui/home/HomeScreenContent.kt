package com.publicissapient.stepchallenge.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.publicissapient.stepchallenge.ui.steps.StepsList
import com.publicissapient.stepchallenge.ui.steps.StepsListViewModel
import com.publicissapient.stepchallenge.ui.users.UserList
import com.publicissapient.stepchallenge.ui.users.UserListViewModel

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    uiState: HomeViewUiState
) {
    Box(modifier = modifier) {
        NavHost(navController, startDestination = HomeViewTabType.USERS_LIST_TAB.data.route) {
            composable(HomeViewTabType.USERS_LIST_TAB.data.route) {
                // Display the list of users
                val viewModel = hiltViewModel<UserListViewModel>()
                UserList(viewModel = viewModel)
            }
            composable(HomeViewTabType.STEPS_LIST_TAB.data.route) {
                // Display the list of steps
                val viewModel = hiltViewModel<StepsListViewModel>()
                StepsList(viewModel = viewModel)
            }
            composable(HomeViewTabType.PROFILE_TAB.data.route) {
                println("Need to implement ui state for Profile: $uiState")
                // Display the user profile
                //UserProfile()
            }
        }
    }
}