package com.rodrigolessinger.thefoodapp.presentation.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rodrigolessinger.thefoodapp.data.model.Recipe
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun RecipeCard(
    recipe: Recipe,
    imageHeight: Dp,
    navigateToDetail: (String) -> Unit
) {
    Card {
        Column(modifier = Modifier.clickable { navigateToDetail(recipe.id) }) {
            CoilImage(
                data = recipe.thumbnail,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.height(imageHeight),
                fadeIn = true,
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }
            )
            MaterialTheme {
                val typography = MaterialTheme.typography

                Column(modifier = Modifier.padding(all = 16.dp)) {
                    Text(
                        text = recipe.description,
                        style = typography.body1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = recipe.name,
                        style = typography.h6,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
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
        ),
        imageHeight = 150.dp
    ) {}
}
