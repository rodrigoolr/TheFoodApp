package com.rodrigolessinger.thefoodapp.presentation.detail.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.rodrigolessinger.thefoodapp.data.model.Recipe
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun SuccessScreen(recipe: Recipe) {
    LazyColumn {
        item { CoilImage(data = recipe.thumbnail, contentDescription = "") }
        item { Text(text = recipe.description) }
        item { Text(text = recipe.name) }
        items(recipe.ingredients) { Text(it) }
        item { Text(text = recipe.instructions) }
    }
}
