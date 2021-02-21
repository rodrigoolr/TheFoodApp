package com.rodrigolessinger.thefoodapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.rodrigolessinger.thefoodapp.presentation.detail.DetailViewModelFactory
import com.rodrigolessinger.thefoodapp.presentation.detail.ui.DetailScreen
import com.rodrigolessinger.thefoodapp.presentation.list.ListViewModel
import com.rodrigolessinger.thefoodapp.presentation.list.ui.ListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MainApp(
    listViewModel: ListViewModel,
    detailViewModelFactory: DetailViewModelFactory
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            listViewModel.load()
            ListScreen(
                viewModel = listViewModel,
                navigateToDetail = { id -> navController.navigate("detail/$id") }
            )
        }

        composable(
            "detail/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("recipeId").orEmpty()
            val viewModel = detailViewModelFactory.build(id)
            viewModel.load()
            DetailScreen(viewModel)
        }
    }
}
