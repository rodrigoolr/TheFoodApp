package com.rodrigolessinger.thefoodapp.data

import com.rodrigolessinger.thefoodapp.data.mealdb.MealDbProvider
import com.rodrigolessinger.thefoodapp.data.mealdb.mealDbModule
import org.koin.dsl.module

val recipeModule = module {
    single<RecipeProvider> { MealDbProvider(get(), get()) }
}

val dataModule = recipeModule + mealDbModule
