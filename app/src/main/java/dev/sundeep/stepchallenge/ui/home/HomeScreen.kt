package dev.sundeep.stepchallenge.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dev.sundeep.stepchallenge.R
import dev.sundeep.stepchallenge.ui.theme.StepChallengeTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = viewModel()) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val selectedTab = when(uiState) {
        is HomeViewUiState.UserListScreen -> HomeViewTabType.USERS_LIST_TAB
        is HomeViewUiState.StepsListScreen -> HomeViewTabType.STEPS_LIST_TAB
        is HomeViewUiState.UserProfileScreen -> HomeViewTabType.PROFILE_TAB
    }
    HomeScreen(modifier, uiState = uiState, selectedTab = selectedTab, homeViewModel::tabClicked)
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeViewUiState,
    selectedTab: HomeViewTabType,
    tabClicked: (HomeViewTabType) -> Unit,
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.home_screen_title)) })
        },
        bottomBar = {
            HomeScreenTabBarNavigation(selectedTab, navController, tabClicked)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //TODO : Implement the Add Step functionality
                },
                backgroundColor = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.home_screen_floating_button_desc),
                    tint = Color.White,
                )
            }
        }
    ) { padding ->
        HomeScreenContent(
            modifier.padding(padding),
            navController,
            uiState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StepChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            HomeScreen(
                uiState = HomeViewUiState.UserProfileScreen,
                selectedTab = HomeViewTabType.PROFILE_TAB,
                tabClicked = {}
            )
        }
    }
}
