package com.rodrigolessinger.thefoodapp.presentation.common.loadable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigolessinger.thefoodapp.presentation.common.loadable.LoadableUiState.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class LoadableViewModel<T>(
    private val dispatcher: CoroutineDispatcher,
    loadOnInit: Boolean = true
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoadableUiState<T>>(Loading)
    val uiState: StateFlow<LoadableUiState<T>> = _uiState

    abstract suspend fun loadData(): T

    private fun performLoad() {
        viewModelScope.launch(dispatcher) {
            _uiState.value = Loading

            try {
                _uiState.value = Success(loadData())
            } catch (_: Exception) {
                _uiState.value = Error
            }
        }
    }

    fun load() {
        performLoad()
    }

    init {
        if (loadOnInit) load()
    }

    fun retry() {
        performLoad()
    }
}
