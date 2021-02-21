package com.rodrigolessinger.thefoodapp.data

import com.rodrigolessinger.thefoodapp.data.model.Recipe

interface RecipeProvider {
    suspend fun getRecipeList(): List<Recipe>
}
