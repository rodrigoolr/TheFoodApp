package com.rodrigolessinger.thefoodapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.rodrigolessinger.thefoodapp.data.RecipeRepository

class DetailViewModelFactory(private val repository: RecipeRepository) : ViewModel() {
    fun build(id: String) = DetailViewModel(id, repository)
}
