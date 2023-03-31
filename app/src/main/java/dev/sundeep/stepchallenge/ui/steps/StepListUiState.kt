package dev.sundeep.stepchallenge.ui.steps

sealed interface StepsListUiState {
    object Loading: StepsListUiState
    data class Success(val steps: List<StepListUiDataModel>): StepsListUiState
    data class Error(val message: String?): StepsListUiState
}