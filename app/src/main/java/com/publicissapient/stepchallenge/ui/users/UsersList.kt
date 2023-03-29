package com.publicissapient.stepchallenge.ui.users

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.publicissapient.stepchallenge.domain.entity.User

@Composable
fun UserList (viewModel: UserListViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UserList(uiState = uiState)
}

@Composable
fun UserList (uiState: UsersListUiState) {
    when(uiState) {
        UsersListUiState.Loading -> LoadingUi()
        is UsersListUiState.Success -> UserListUi(users = uiState.users)
    }

}

@Composable
fun LoadingUi() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading...")
    }
}

@Composable
fun UserListUi(users: List<User>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(users) { user ->
            UserListItem(user = user)
        }
    }
}

@Composable
fun UserListItem(user: User) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.subtitle1
            )
        }
        if (user.isAdmin) {
            Text(
                text = "Admin",
                style = MaterialTheme.typography.subtitle1,
                color = Color.Green,
                textAlign = TextAlign.Right
            )
        }
    }
}