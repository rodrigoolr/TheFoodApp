package com.rodrigolessinger.thefoodapp.presentation.common.loadable

sealed class LoadableUiState<out T> {
    data class Success<T>(val data: T) : LoadableUiState<T>()
    object Error : LoadableUiState<Nothing>()
    object Loading : LoadableUiState<Nothing>()
}
