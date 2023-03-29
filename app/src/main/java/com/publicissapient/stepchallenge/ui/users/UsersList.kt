package com.publicissapient.stepchallenge.ui.users

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.publicissapient.stepchallenge.R
import com.publicissapient.stepchallenge.domain.entity.User
import com.publicissapient.stepchallenge.ui.common.BasicListUi
import com.publicissapient.stepchallenge.ui.common.ErrorUi
import com.publicissapient.stepchallenge.ui.common.LoadingUi
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserList (modifier: Modifier = Modifier, viewModel: UserListViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UserList(modifier, uiState = uiState)
}

@Composable
fun UserList (modifier: Modifier = Modifier, uiState: UsersListUiState) {
    when(uiState) {
        UsersListUiState.Loading -> LoadingUi()
        is UsersListUiState.Success -> BasicListUi<User>(
            modifier = modifier,
            data = uiState.users,
            listItem = { UserListItem(user = it) }
        )
        is UsersListUiState.Error -> ErrorUi(
            modifier = modifier,
            message = uiState.message ?: stringResource(id = R.string.default_error_message_with_retry))
    }

}

