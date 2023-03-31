package dev.sundeep.stepchallenge.ui.steps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sundeep.stepchallenge.domain.usecase.GetStepsDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepsListViewModel @Inject constructor(
    private val stepsUseCase: GetStepsDataUseCase
): ViewModel() {
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
                        val stepsListData = it.map { stepData ->
                            StepListUiDataModel(stepData)
                        }
                        _uiState.value = StepsListUiState.Success(stepsListData)
                    },
                    onFailure = {
                        _uiState.value = StepsListUiState.Error(it.message)
                    }
                )
            }
        }
    }
}