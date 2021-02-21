package com.rodrigolessinger.thefoodapp.presentation.list

import com.rodrigolessinger.thefoodapp.data.model.Recipe

sealed class ListUiState {
    data class Success(val recipes: List<Recipe>) : ListUiState()
    object Error : ListUiState()
    object Loading : ListUiState()
}
