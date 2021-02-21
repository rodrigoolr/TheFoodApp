package com.rodrigolessinger.thefoodapp.presentation.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.rodrigolessinger.thefoodapp.presentation.common.ErrorScreen
import com.rodrigolessinger.thefoodapp.presentation.common.LoadingScreen
import com.rodrigolessinger.thefoodapp.presentation.list.ListUiState
import com.rodrigolessinger.thefoodapp.presentation.list.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun ListScreen(viewModel: ListViewModel, navigateToDetail: (String) -> Unit) {
    when (val uiState = viewModel.uiState.collectAsState().value) {
        is ListUiState.Success -> SuccessScreen(
            recipes = uiState.recipes,
            navigateToDetail = navigateToDetail
        )
        is ListUiState.Error -> ErrorScreen(onRetry = viewModel::retry)
        is ListUiState.Loading -> LoadingScreen()
    }
}
