package com.rodrigolessinger.thefoodapp.data.mealdb

import com.rodrigolessinger.thefoodapp.BuildConfig
import com.rodrigolessinger.thefoodapp.data.RecipeProvider

class MealDbProvider(
    private val service: MealDbService,
    private val adapter: MealDbAdapter
) : RecipeProvider {

    override suspend fun getRecipeList() =
        service.getLatestRecipes(BuildConfig.MEALDB_API_KEY)
            .let(adapter::mapModel)
}
