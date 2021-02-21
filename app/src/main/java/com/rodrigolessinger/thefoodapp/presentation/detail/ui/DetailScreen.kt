package com.rodrigolessinger.thefoodapp.presentation.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.rodrigolessinger.thefoodapp.presentation.common.ErrorScreen
import com.rodrigolessinger.thefoodapp.presentation.common.LoadingScreen
import com.rodrigolessinger.thefoodapp.presentation.detail.DetailUiState
import com.rodrigolessinger.thefoodapp.presentation.detail.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun DetailScreen(viewModel: DetailViewModel) {
    when (val uiState = viewModel.uiState.collectAsState().value) {
        is DetailUiState.Success -> SuccessScreen(uiState.recipe)
        is DetailUiState.Error -> ErrorScreen(onRetry = viewModel::retry)
        is DetailUiState.Loading -> LoadingScreen()
    }
}
