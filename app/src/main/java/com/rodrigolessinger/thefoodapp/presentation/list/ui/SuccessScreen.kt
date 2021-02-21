package com.rodrigolessinger.thefoodapp.presentation.list.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigolessinger.thefoodapp.data.model.Recipe

@Composable
fun SuccessScreen(recipes: List<Recipe>, navigateToDetail: (String) -> Unit) {
    LazyColumn {
        items(recipes) { RecipeCard(it, navigateToDetail) }
    }
}

@Preview
@Composable
fun SuccessScreenPreview() {
    SuccessScreen(
        listOf(
            Recipe(
                id = "123",
                name = "Delicious Meal",
                description = "From the World",
                thumbnail = "url.to.image",
                ingredients = listOf(),
                instructions = ""
            ),
            Recipe(
                id = "456",
                name = "Not so great meal",
                description = "From Somewhere",
                thumbnail = "other.image",
                ingredients = listOf(),
                instructions = ""
            ),
        )
    ) {}
}
