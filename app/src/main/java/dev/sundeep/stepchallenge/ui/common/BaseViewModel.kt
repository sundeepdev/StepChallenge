package dev.sundeep.stepchallenge.ui.common

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

    private val _viewEvents = Channel<VI?>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    private val _viewActions = Channel<SE>(Channel.BUFFERED)

}