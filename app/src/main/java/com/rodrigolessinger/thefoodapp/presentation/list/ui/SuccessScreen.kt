package com.rodrigolessinger.thefoodapp.presentation.list.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rodrigolessinger.thefoodapp.data.model.Recipe

@Composable
fun SuccessScreen(
    recipes: List<Recipe>,
    gridBuilder: GridBuilder = GridBuilder(),
    minimumColumnWidth: Dp = 150.dp,
    navigateToDetail: (String) -> Unit
) {
    BoxWithConstraints {
        val numberOfColumns = (maxWidth / minimumColumnWidth).toInt()
        val columnWidth = maxWidth / numberOfColumns

        val grid = remember(maxWidth) { gridBuilder.build(recipes, numberOfColumns) }

        LazyColumn {
            items(grid) { row ->
                Row {
                    for (item in row) {
                        RecipeCard(
                            recipe = item,
                            modifier = Modifier.width(columnWidth),
                            navigateToDetail = navigateToDetail
                        )
                    }
                }
            }
        }
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
