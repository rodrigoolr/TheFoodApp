package com.rodrigolessinger.thefoodapp.presentation.detail

import com.rodrigolessinger.thefoodapp.data.model.Recipe

sealed class DetailUiState {
    data class Success(val recipe: Recipe) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}
