package com.rodrigolessinger.thefoodapp.presentation.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rodrigolessinger.thefoodapp.data.model.Recipe
import com.rodrigolessinger.thefoodapp.presentation.common.VerticallyExpandedRow
import com.rodrigolessinger.thefoodapp.presentation.list.logic.GridBuilder

@Composable
fun ListScreen(
    recipes: List<Recipe>,
    gridBuilder: GridBuilder = GridBuilder(),
    itemPadding: Dp = 8.dp,
    minimumColumnWidth: Dp = 150.dp,
    navigateToDetail: (String) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.padding(all = itemPadding)) {
        val numberOfColumns = (maxWidth / minimumColumnWidth).toInt()
        val columnWidth = (maxWidth / numberOfColumns) - itemPadding
        val grid = remember(maxWidth) { gridBuilder.build(recipes, numberOfColumns) }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(itemPadding)) {
            items(grid) { row ->
                VerticallyExpandedRow(itemPadding = itemPadding) {
                    for (item in row) {
                        RecipeCard(
                            recipe = item,
                            imageHeight = columnWidth,
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
fun ListScreenPreview() {
    ListScreen(
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
            Recipe(
                id = "789",
                name = "Third Meal",
                description = "Another Place",
                thumbnail = "image.3",
                ingredients = listOf(),
                instructions = ""
            ),
        )
    ) {}
}
