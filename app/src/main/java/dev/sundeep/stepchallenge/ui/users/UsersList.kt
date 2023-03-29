package dev.sundeep.stepchallenge.ui.users

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.sundeep.stepchallenge.R
import dev.sundeep.stepchallenge.ui.common.view.BasicListUi
import dev.sundeep.stepchallenge.ui.common.view.ErrorUi
import dev.sundeep.stepchallenge.ui.common.view.LoadingUi

@Composable
fun UserList (modifier: Modifier = Modifier, viewModel: UserListViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UserList(modifier, uiState = uiState)
}

@Composable
fun UserList (modifier: Modifier = Modifier, uiState: UsersListUiState) {
    when(uiState) {
        UsersListUiState.Loading -> LoadingUi()
        is UsersListUiState.Success -> BasicListUi(
            modifier = modifier,
            data = uiState.users,
            listItem = { UserListItem(user = it) }
        )
        is UsersListUiState.Error -> ErrorUi(
            modifier = modifier,
            message = uiState.message ?: stringResource(id = R.string.default_error_message_with_retry))
    }

}

