package com.publicissapient.stepchallenge.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.publicissapient.stepchallenge.ui.steps.StepsList
import com.publicissapient.stepchallenge.ui.users.UserList

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    uiState: HomeViewUiState
) {
    Box(modifier = modifier) {
        NavHost(navController, startDestination = HomeViewTabType.USERS_LIST_TAB.tabData.title) {
            composable(HomeViewTabType.USERS_LIST_TAB.tabData.title) {
                // Display the list of users
                if (uiState is HomeViewUiState.UserListScreen) {
                    UserList(viewModel = uiState.userListViewModel)
                } else {
                    println("UiState didn't match: $uiState")
                }
            }
            composable(HomeViewTabType.STEPS_LIST_TAB.tabData.title) {
                // Display the list of steps
                if (uiState is HomeViewUiState.StepsListScreen) {
                    StepsList(uiState.stepsListViewModel)
                } else {
                    println("UiState didn't match: $uiState")
                }
            }
            composable(HomeViewTabType.PROFILE_TAB.tabData.title) {
                println("Need to implement ui state: $uiState")
                // Display the user profile
                //UserProfile()
            }
        }
    }
}