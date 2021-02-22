package com.rodrigolessinger.thefoodapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.rodrigolessinger.thefoodapp.koin.getViewModel
import com.rodrigolessinger.thefoodapp.presentation.common.loadable.LoadableScreen
import com.rodrigolessinger.thefoodapp.presentation.list.ListViewModel
import com.rodrigolessinger.thefoodapp.presentation.list.ui.ListScreen
import com.rodrigolessinger.thefoodapp.presentation.recipe.RecipeViewModel
import com.rodrigolessinger.thefoodapp.presentation.recipe.ui.RecipeScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.parameter.parametersOf

@ExperimentalCoroutinesApi
@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            val viewModel = getViewModel<ListViewModel>()
            LoadableScreen(viewModel) {
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
            val viewModel = getViewModel<RecipeViewModel> { parametersOf(id) }
            LoadableScreen(viewModel) { RecipeScreen(recipe = it) }
        }
    }
}
