package com.publicissapient.stepchallenge.ui.steps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.publicissapient.stepchallenge.domain.entity.StepData
import com.publicissapient.stepchallenge.domain.usecase.StepsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface StepsListUiState {
    object Loading: StepsListUiState
    data class Success(val steps: List<StepData>): StepsListUiState
    data class Error(val message: String?): StepsListUiState
}

@HiltViewModel
class StepsListViewModel @Inject constructor(
    private val stepsUseCase: StepsUseCase
): ViewModel() {

//    private val steps: Flow<List<StepData>> = stepsUseCase.getStepsData("1")
//
//    val uiState: StateFlow<StepsListUiState> = steps
//        .map {
//            StepsListUiState.Success(it)
//        }
//        .stateIn(
//            scope = viewModelScope,
//            initialValue = StepsListUiState.Loading,
//            started = SharingStarted.WhileSubscribed(5_000L)
//        )

    init {
        fetchLatestStepsData()
    }

    private val _uiState = MutableStateFlow<StepsListUiState>(StepsListUiState.Loading)
    val uiState: StateFlow<StepsListUiState>
        get() = _uiState

    private fun fetchLatestStepsData() {
        viewModelScope.launch {
            stepsUseCase.getStepsData().collect { result ->
                result.fold(
                    onSuccess = {
                        _uiState.value = StepsListUiState.Success(it)
                    },
                    onFailure = {
                        _uiState.value = StepsListUiState.Error(it.message)
                    }
                )
            }
        }
    }
}