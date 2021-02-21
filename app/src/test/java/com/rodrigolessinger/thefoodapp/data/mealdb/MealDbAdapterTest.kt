package com.rodrigolessinger.thefoodapp.data.mealdb

import com.rodrigolessinger.thefoodapp.data.mealdb.model.Meal
import com.rodrigolessinger.thefoodapp.data.mealdb.model.MealList
import com.rodrigolessinger.thefoodapp.data.model.Recipe
import org.junit.Assert.assertEquals
import org.junit.Test

class MealDbAdapterTest {

    private val adapter = MealDbAdapter()

    private fun testMapModel(mealList: List<Meal>, expected: List<Recipe>) =
        assertEquals(adapter.mapModel(MealList(mealList)), expected)

    private fun testMapModel(meal: Meal, expected: Recipe) =
        testMapModel(listOf(meal), listOf(expected))

    @Test
    fun testMapModel_withEmptyMealList_returnsEmptyList() {
        testMapModel(listOf(), listOf())
    }

    @Test
    fun testMapModel_withOneMeal_returnsCorrectDescriptions() {
        testMapModel(
            Meal(
                id = "1",
                meal = "Name of Recipe",
                category = "Random",
                area = "Somewhere",
                thumbnail = "url.to.image",
                instructions = "Do something"
            ),
            Recipe(
                id = "1",
                name = "Name of Recipe",
                description = "Somewhere - Random",
                thumbnail = "url.to.image",
                ingredients = listOf(),
                instructions = "Do something"
            )
        )
        testMapModel(
            Meal(
                id = "2",
                meal = "Second Recipe",
                category = "Others",
                area = "Europe",
                thumbnail = "other.url",
                instructions = "Mix everything"
            ),
            Recipe(
                id = "2",
                name = "Second Recipe",
                description = "Europe - Others",
                thumbnail = "other.url",
                ingredients = listOf(),
                instructions = "Mix everything"
            )
        )
    }

    @Test
    fun testMapModel_withOneMeal_returnsCorrectIngredients() {
        testMapModel(
            Meal(
                id = "Id",
                meal = "Name",
                category = "Category",
                area = "Area",
                thumbnail = "url.to.image",
                measure1 = "1kg",
                ingredient1 = "Flour",
                measure2 = "500g",
                ingredient3 = "Garlic",
                instructions = "Instructions"
            ),
            Recipe(
                id = "Id",
                name = "Name",
                description = "Area - Category",
                thumbnail = "url.to.image",
                ingredients = listOf(
                    "1kg Flour"
                ),
                instructions = "Instructions"
            )
        )
        testMapModel(
            Meal(
                id = "Id",
                meal = "Name",
                category = "Category",
                area = "Area",
                thumbnail = "url.to.image",
                measure2 = "5g",
                ingredient2 = "Salt",
                measure4 = "10g",
                ingredient4 = "Black Pepper",
                measure20 = "150ml",
                ingredient20 = "Water",
                instructions = "Instructions"
            ),
            Recipe(
                id = "Id",
                name = "Name",
                description = "Area - Category",
                thumbnail = "url.to.image",
                ingredients = listOf(
                    "5g Salt",
                    "10g Black Pepper",
                    "150ml Water"
                ),
                instructions = "Instructions"
            )
        )
    }

    @Test
    fun testMapModel_withMultipleMeals_returnsCorrectModels() {
        testMapModel(
            listOf(
                Meal(
                    id = "Id1",
                    meal = "Recipe 1",
                    category = "Category 1",
                    area = "Area 1",
                    thumbnail = "http://url/1",
                    instructions = "Make 1"
                ),
                Meal(
                    id = "Id2",
                    meal = "Recipe 2",
                    category = "Category 2",
                    area = "Area 2",
                    thumbnail = "http://url/2",
                    measure1 = "1g",
                    ingredient1 = "Something",
                    instructions = "Make 2"
                ),
                Meal(
                    id = "Id3",
                    meal = "Recipe 3",
                    category = "Category 3",
                    area = "Area 3",
                    thumbnail = "http://url/3",
                    measure1 = "Measure 1",
                    ingredient1 = "Ingredient 1",
                    measure2 = "Measure 2",
                    ingredient2 = "Ingredient 2",
                    measure3 = "Measure 3",
                    ingredient3 = "Ingredient 3",
                    measure4 = "Measure 4",
                    ingredient4 = "Ingredient 4",
                    instructions = "Make 3"
                ),
            ),
            listOf(
                Recipe(
                    id = "Id1",
                    name = "Recipe 1",
                    description = "Area 1 - Category 1",
                    thumbnail = "http://url/1",
                    ingredients = listOf(),
                    instructions = "Make 1"
                ),
                Recipe(
                    id = "Id2",
                    name = "Recipe 2",
                    description = "Area 2 - Category 2",
                    thumbnail = "http://url/2",
                    ingredients = listOf(
                        "1g Something"
                    ),
                    instructions = "Make 2"
                ),
                Recipe(
                    id = "Id3",
                    name = "Recipe 3",
                    description = "Area 3 - Category 3",
                    thumbnail = "http://url/3",
                    ingredients = listOf(
                        "Measure 1 Ingredient 1",
                        "Measure 2 Ingredient 2",
                        "Measure 3 Ingredient 3",
                        "Measure 4 Ingredient 4",
                    ),
                    instructions = "Make 3"
                ),
            )

        )
    }

}
