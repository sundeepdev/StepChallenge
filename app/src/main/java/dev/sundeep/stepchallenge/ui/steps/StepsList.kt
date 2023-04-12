package dev.sundeep.stepchallenge.ui.steps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.sundeep.stepchallenge.R
import dev.sundeep.stepchallenge.ui.common.view.BasicListUi
import dev.sundeep.stepchallenge.ui.common.view.ErrorUi
import dev.sundeep.stepchallenge.ui.common.view.LoadingUi
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StepsList(viewModel: StepsListViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    StepsList(uiState = uiState)
    viewModel.onViewReady()
}

@Composable
fun StepsList(uiState: StepsListUiState) {
    when(uiState) {
        StepsListUiState.Loading -> LoadingUi()
        is StepsListUiState.Success -> BasicListUi(
            data = uiState.steps,
            listItem = { StepListItem(it) }
        )
        is StepsListUiState.Error -> ErrorUi(
            message = uiState.message ?: stringResource(id = R.string.default_error_message_with_retry)
        )
    }
}