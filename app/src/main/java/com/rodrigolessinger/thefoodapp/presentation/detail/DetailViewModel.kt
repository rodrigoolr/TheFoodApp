package com.rodrigolessinger.thefoodapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigolessinger.thefoodapp.data.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val id: String,
    private val repository: RecipeRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    private fun loadRecipe() {
        viewModelScope.launch(dispatcher) {
            _uiState.value = DetailUiState.Loading
            try {
                val recipe = repository.getRecipe(id)
                if (recipe == null) {
                    _uiState.value = DetailUiState.Error
                } else {
                    _uiState.value = DetailUiState.Success(recipe)
                }
            } catch (_: Exception) {
                _uiState.value = DetailUiState.Error
            }
        }
    }

    fun load() {
        loadRecipe()
    }

    fun retry() {
        loadRecipe()
    }
}
