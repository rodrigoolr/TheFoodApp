package com.rodrigolessinger.thefoodapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigolessinger.thefoodapp.data.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: RecipeRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiState: StateFlow<ListUiState> = _uiState

    private fun loadRecipes() {
        viewModelScope.launch(dispatcher) {
            _uiState.value = ListUiState.Loading
            try {
                _uiState.value = ListUiState.Success(repository.getRecipeList())
            } catch (_: Exception) {
                _uiState.value = ListUiState.Error
            }
        }
    }

    fun load() {
        loadRecipes()
    }

    fun retry() {
        loadRecipes()
    }
}
