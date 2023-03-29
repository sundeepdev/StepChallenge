package com.publicissapient.stepchallenge.ui.steps

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.publicissapient.stepchallenge.domain.entity.StepData
import com.publicissapient.stepchallenge.ui.users.LoadingUi

@Composable
fun StepsList(viewModel: StepsListViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    StepsList(uiState = uiState)
}

@Composable
fun StepsList(uiState: StepsListUiState) {
    when(uiState) {
        StepsListUiState.Loading -> LoadingUi()
        is StepsListUiState.Success -> StepsListUi(steps = uiState.steps)
    }
}

@Composable
fun StepsListUi(steps: List<StepData>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(steps) { step ->
            StepListItem(step = step)
        }
    }
}

@Composable
fun StepListItem(step: StepData) {
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
                text = "${step.count}",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = step.stepDataDisplayDate(),
                color = Color.DarkGray,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}