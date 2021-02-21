package com.rodrigolessinger.thefoodapp.helper

import com.rodrigolessinger.thefoodapp.data.model.Recipe

object MockRecipe {
    val defaultRecipe = Recipe(
        id = "123",
        name = "Recipe Name",
        description = "Description",
        thumbnail = "http://www.images.com/some-url",
        ingredients = listOf("1kg Flour", "2kg Salt", "3kg Water"),
        instructions = "Mix Everything and it's done"
    )

    val defaultRecipeList = listOf(defaultRecipe)
}
