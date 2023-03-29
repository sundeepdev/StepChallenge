package com.publicissapient.stepchallenge.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

data class HomeViewTabDataModel(val title: String, val icon: ImageVector, val route: String = title)

enum class HomeViewTabType(val tabData: HomeViewTabDataModel) {
    USERS_LIST_TAB(HomeViewTabDataModel("Users", Icons.Default.Home)),
    STEPS_LIST_TAB(HomeViewTabDataModel("Steps", Icons.Default.ThumbUp)),
    PROFILE_TAB(HomeViewTabDataModel("Profile", Icons.Default.Person))
}