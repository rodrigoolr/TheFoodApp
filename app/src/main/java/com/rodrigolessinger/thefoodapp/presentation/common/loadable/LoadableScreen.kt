package com.rodrigolessinger.thefoodapp.presentation.common.loadable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.rodrigolessinger.thefoodapp.presentation.common.loadable.LoadableUiState.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun <T> LoadableScreen(
    uiState: LoadableUiState<T>,
    onRetry: () -> Unit,
    composeSuccess: @Composable (T) -> Unit
) {
    when (uiState) {
        is Success -> composeSuccess(uiState.data)
        is Error -> ErrorScreen(onRetry = onRetry)
        is Loading -> LoadingScreen()
    }
}

@Composable
@ExperimentalCoroutinesApi
fun <T> LoadableScreen(
    viewModel: LoadableViewModel<T>,
    composeSuccess: @Composable() (T) -> Unit
) {
    LoadableScreen(
        uiState = viewModel.uiState.collectAsState().value,
        onRetry = viewModel::retry,
        composeSuccess = composeSuccess
    )
}
