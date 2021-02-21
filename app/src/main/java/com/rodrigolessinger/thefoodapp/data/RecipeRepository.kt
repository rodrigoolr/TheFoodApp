package com.rodrigolessinger.thefoodapp.data

import com.rodrigolessinger.thefoodapp.data.model.Recipe

class RecipeRepository(private val recipeProvider: RecipeProvider) {

    private var localCache: List<Recipe> = listOf()

    suspend fun getRecipeList(): List<Recipe> =
        recipeProvider.getRecipeList()
            .apply { localCache = this }

    private fun List<Recipe>.findById(id: String) =
        firstOrNull { recipe -> recipe.id == id }

    suspend fun getRecipe(id: String): Recipe? =
        localCache.findById(id) ?: getRecipeList().findById(id)

}
