package com.rodrigolessinger.thefoodapp.presentation.list.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigolessinger.thefoodapp.data.model.Recipe

@Composable
fun SuccessScreen(recipes: List<Recipe>) {
    LazyColumn {
        items(recipes) { RecipeCard(it) }
    }
}

@Preview
@Composable
fun SuccessScreenPreview() {
    SuccessScreen(
        listOf(
            Recipe(
                name = "Delicious Meal",
                description = "From the World",
                thumbnail = "url.to.image"
            ),
            Recipe(
                name = "Not so great meal",
                description = "From Somewhere",
                thumbnail = "other.image"
            ),
        )
    )
}
