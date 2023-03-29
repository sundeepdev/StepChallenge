package com.publicissapient.stepchallenge.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.publicissapient.stepchallenge.ui.steps.StepsList
import com.publicissapient.stepchallenge.ui.theme.OneOnOneTheme
import com.publicissapient.stepchallenge.ui.users.UserList

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    var selectedTab = when(uiState) {
        is HomeViewUiState.UserListScreen -> HomeViewTab.USERS_LIST_TAB
        is HomeViewUiState.StepsListScreen -> HomeViewTab.STEPS_LIST_TAB
        is HomeViewUiState.UserProfileScreen -> HomeViewTab.PROFILE_TAB
    }
    HomeScreen( uiState = uiState, selectedTab = selectedTab, homeViewModel::tabClicked)
}

@Composable
fun HomeScreen(
    uiState: HomeViewUiState,
    selectedTab: HomeViewTab,
    tabClicked: (HomeViewTab) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Step Challenge") })
        },
        bottomBar = {
            TabBar(selectedTab, navController, tabClicked)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* ... */ }) {
                /* FAB content */
            }
        }
    ) { padding ->
        HomeScreenContent(padding, navController, uiState)
    }
}

@Composable
private fun HomeScreenContent(
    padding: PaddingValues,
    navController: NavHostController,
    uiState: HomeViewUiState
) {
    Box(modifier = Modifier.padding(padding)) {
        NavHost(navController, startDestination = HomeViewTab.USERS_LIST_TAB.tabData.title) {
            composable(HomeViewTab.USERS_LIST_TAB.tabData.title) {
                // Display the list of users
                if (uiState is HomeViewUiState.UserListScreen) {
                    UserList(uiState.userListViewModel)
                } else {

                }
            }
            composable(HomeViewTab.STEPS_LIST_TAB.tabData.title) {
                // Display the list of steps
                if (uiState is HomeViewUiState.StepsListScreen) {
                    StepsList(uiState.stepsListViewModel)
                }
            }
            composable(HomeViewTab.PROFILE_TAB.tabData.title) {

                // Display the user profile
                //UserProfile()
            }
        }
    }
}

@Composable
private fun TabBar(
    selectedTab: HomeViewTab,
    navController: NavHostController,
    tabClicked: (HomeViewTab) -> Unit
) {
    BottomNavigation {
        HomeViewTab.values().forEach { tab ->
            BottomNavigationItem(
                selected = selectedTab == tab,
                onClick = {
                    navController.navigate(tab.tabData.title) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                    }
                    tabClicked(tab)
                },
                label = { Text(tab.tabData.title) },
                icon = { Icon(tab.tabData.icon, contentDescription = null) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OneOnOneTheme {
//        HomeScreen()
    }
}
