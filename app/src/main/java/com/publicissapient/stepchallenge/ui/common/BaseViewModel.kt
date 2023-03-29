package com.publicissapient.stepchallenge.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<VS, VI, SE>: ViewModel() {

    protected abstract val initialViewState: VS
    protected abstract fun processAction(action: VI)

    protected var lastViewState: VS = initialViewState

    private val _viewState = MutableStateFlow(initialViewState)
    val viewState = _viewState.asStateFlow()

    private val _viewEvents = Channel<SE?>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    private val _viewActions = Channel<VI>(Channel.BUFFERED)

}