package com.rodrigolessinger.thefoodapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.rodrigolessinger.thefoodapp.presentation.common.loadable.LoadableScreen
import com.rodrigolessinger.thefoodapp.presentation.list.ListViewModel
import com.rodrigolessinger.thefoodapp.presentation.list.ui.ListScreen
import com.rodrigolessinger.thefoodapp.presentation.recipe.RecipeViewModel
import com.rodrigolessinger.thefoodapp.presentation.recipe.ui.RecipeScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MainApp(
    listViewModel: ListViewModel,
    recipeViewModelFactory: RecipeViewModel.Factory
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            listViewModel.load()
            LoadableScreen(listViewModel) {
                ListScreen(
                    recipes = it,
                    navigateToDetail = { id -> navController.navigate("detail/$id") }
                )
            }
        }

        composable(
            "detail/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("recipeId").orEmpty()
            val viewModel = recipeViewModelFactory.build(id).apply { load() }
            LoadableScreen(viewModel) { RecipeScreen(recipe = it) }
        }
    }
}
