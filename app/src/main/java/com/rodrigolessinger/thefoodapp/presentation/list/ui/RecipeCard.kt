package com.rodrigolessinger.thefoodapp.presentation.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigolessinger.thefoodapp.data.model.Recipe
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun RecipeCard(recipe: Recipe) {
    Card {
        Column {
            CoilImage(data = recipe.thumbnail, contentDescription = "")
            Text(text = recipe.description)
            Text(text = recipe.name)
        }
    }
}

@Preview
@Composable
fun RecipeCardPreview() {
    RecipeCard(
        Recipe(
            id = "123",
            name = "Delicious Meal",
            description = "Meal Description",
            thumbnail = "url.to.image",
            ingredients = listOf(),
            instructions = ""
        )
    )
}
