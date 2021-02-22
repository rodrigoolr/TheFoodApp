package com.rodrigolessinger.thefoodapp.presentation.recipe

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recipeScreenModule = module {
    single { RecipeViewModel.Factory(get()) }
    viewModel { (id: String) -> get<RecipeViewModel.Factory>().build(id) }
}
