package com.rodrigolessinger.thefoodapp.presentation.recipe

import org.koin.dsl.module

val recipeScreenModule = module {
    single { RecipeViewModel.Factory(get()) }
}
