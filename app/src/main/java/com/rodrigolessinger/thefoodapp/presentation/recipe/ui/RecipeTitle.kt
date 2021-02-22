package com.rodrigolessinger.thefoodapp.presentation.recipe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecipeTitle(name: String, description: String) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = name,
                style = typography.h4,
                textAlign = TextAlign.Center,
            )
            Text(
                text = description,
                style = typography.subtitle1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun RecipeTitlePreview() {
    RecipeTitle("Delicious Meal", "Meal Description")
}
