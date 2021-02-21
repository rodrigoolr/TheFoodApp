package com.rodrigolessinger.thefoodapp.presentation.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.rodrigolessinger.thefoodapp.presentation.list.ListUiState
import com.rodrigolessinger.thefoodapp.presentation.list.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun ListScreen(viewModel: ListViewModel) {
    when (val uiState = viewModel.uiState.collectAsState().value) {
        is ListUiState.Success -> SuccessScreen(uiState.recipes)
        is ListUiState.Error -> ErrorScreen(onRetry = viewModel::retry)
        is ListUiState.Loading -> LoadingScreen()
    }
}
