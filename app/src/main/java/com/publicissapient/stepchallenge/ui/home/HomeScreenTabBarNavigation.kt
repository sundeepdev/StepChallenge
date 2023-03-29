package com.publicissapient.stepchallenge.ui.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun HomeScreenTabBarNavigation(
    selectedTab: HomeViewTabType,
    navController: NavHostController,
    tabClicked: (HomeViewTabType) -> Unit
) {
    BottomNavigation {
        HomeViewTabType.values().forEach { tab ->
            BottomNavigationItem(
                selected = selectedTab == tab,
                onClick = {
                    navController.navigate(tab.data.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                    }
                    tabClicked(tab)
                },
                label = { Text(tab.data.title) },
                icon = { Icon(tab.data.icon, contentDescription = "${tab.data.title} Tab Button") }
            )
        }
    }
}