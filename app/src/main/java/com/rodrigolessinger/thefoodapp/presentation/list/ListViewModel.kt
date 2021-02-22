package com.rodrigolessinger.thefoodapp.presentation.list

import com.rodrigolessinger.thefoodapp.data.RecipeRepository
import com.rodrigolessinger.thefoodapp.data.model.Recipe
import com.rodrigolessinger.thefoodapp.presentation.common.loadable.LoadableViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ListViewModel(
    private val repository: RecipeRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Main
) : LoadableViewModel<List<Recipe>>(dispatcher) {

    override suspend fun loadData() = repository.getRecipeList()
}
