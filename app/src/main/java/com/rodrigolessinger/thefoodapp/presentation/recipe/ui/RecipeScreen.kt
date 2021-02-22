package com.rodrigolessinger.thefoodapp.presentation.recipe.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.rodrigolessinger.thefoodapp.data.model.Recipe

@Composable
fun RecipeScreen(recipe: Recipe) {
    BoxWithConstraints {
        val maxHeight = with(LocalDensity.current) { constraints.maxHeight.toDp() }
        val imageHeight = maxHeight * 0.6f

        ConstraintLayout {
            val scrollState = rememberScrollState()

            RecipeImage(
                thumbnail = recipe.thumbnail,
                imageHeight = imageHeight,
                scrollValue = scrollState.value
            )

            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(imageHeight))

                Box(
                    modifier = Modifier.heightIn(min = maxHeight)
                ) {
                    Column(modifier = Modifier.padding(all = 16.dp).fillMaxWidth()) {
                        RecipeTitle(recipe.name, recipe.description)

                        Spacer(modifier = Modifier.height(32.dp))

                        RecipeIngredients(recipe.ingredients)

                        Spacer(modifier = Modifier.height(32.dp))

                        RecipeInstructions(recipe.instructions)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeScreenPreview() {
    RecipeScreen(
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
