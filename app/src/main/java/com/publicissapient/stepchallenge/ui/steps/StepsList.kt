package com.publicissapient.stepchallenge.ui.steps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.publicissapient.stepchallenge.R
import com.publicissapient.stepchallenge.ui.common.BasicListUi
import com.publicissapient.stepchallenge.ui.common.ErrorUi
import com.publicissapient.stepchallenge.ui.common.LoadingUi
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StepsList(viewModel: StepsListViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    StepsList(uiState = uiState)
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