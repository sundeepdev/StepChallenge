package com.publicissapient.stepchallenge.ui.steps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.publicissapient.stepchallenge.domain.entity.StepData
import com.publicissapient.stepchallenge.domain.usecase.StepsUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

sealed interface StepsListUiState {
    object Loading: StepsListUiState
    data class Success(val steps: List<StepData>): StepsListUiState
}

class StepsListViewModel @Inject constructor(
    private val stepsUseCase: StepsUseCase
): ViewModel() {

    private val steps: Flow<List<StepData>> = stepsUseCase.getStepsData("1")

    val uiState: StateFlow<StepsListUiState> = steps
        .map {
            StepsListUiState.Success(it)
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = StepsListUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000L)
        )
}