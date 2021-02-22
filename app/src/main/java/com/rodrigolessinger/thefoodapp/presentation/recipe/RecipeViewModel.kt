package com.rodrigolessinger.thefoodapp.presentation.recipe

import com.rodrigolessinger.thefoodapp.data.RecipeRepository
import com.rodrigolessinger.thefoodapp.data.model.Recipe
import com.rodrigolessinger.thefoodapp.presentation.common.loadable.LoadableViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class RecipeViewModel(
    private val id: String,
    private val repository: RecipeRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Main
) : LoadableViewModel<Recipe>(dispatcher) {

    class RecipeNotFoundException : Exception()

    class Factory(
        private val repository: RecipeRepository,
        private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    ) {
        fun build(id: String) = RecipeViewModel(id, repository, dispatcher)
    }

    override suspend fun loadData() = repository.getRecipe(id) ?: throw RecipeNotFoundException()
}
