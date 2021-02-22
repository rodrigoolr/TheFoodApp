package com.rodrigolessinger.thefoodapp.presentation.recipe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigolessinger.thefoodapp.R

@Composable
fun RecipeIngredients(ingredients: List<String>) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            verticalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.Top
            )
        ) {
            Text(
                text = stringResource(R.string.recipe_screen_ingredients_title),
                style = typography.h5
            )
            for (ingredient in ingredients) {
                Text(
                    text = stringResource(
                        R.string.recipe_screen_ingredients_ingredient,
                        ingredient
                    ),
                    style = typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
fun RecipeIngredientsPreview() {
    RecipeIngredients(listOf("300g Flour", "5g Salt"))
}
