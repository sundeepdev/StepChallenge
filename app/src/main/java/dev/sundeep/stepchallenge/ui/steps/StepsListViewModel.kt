package dev.sundeep.stepchallenge.ui.steps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sundeep.stepchallenge.domain.entity.StepData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sundeep.stepchallenge.domain.usecase.GetStepsDataUseCase
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
    private val stepsUseCase: GetStepsDataUseCase
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
            stepsUseCase().collect { result ->
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