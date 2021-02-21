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
                meal = "Name of Recipe",
                category = "Random",
                area = "Somewhere",
                thumbnail = "url.to.image"
            ),
            Recipe(
                name = "Name of Recipe",
                description = "Somewhere - Random",
                thumbnail = "url.to.image"
            )
        )
        testMapModel(
            Meal(
                meal = "Second Recipe",
                category = "Others",
                area = "Europe",
                thumbnail = "other.url"
            ),
            Recipe(
                name = "Second Recipe",
                description = "Europe - Others",
                thumbnail = "other.url"
            )
        )
    }

    @Test
    fun testMapModel_withMultipleMeals_returnsCorrectModels() {
        testMapModel(
            listOf(
                Meal(
                    meal = "Recipe 1",
                    category = "Category 1",
                    area = "Area 1",
                    thumbnail = "http://url/1"
                ),
                Meal(
                    meal = "Recipe 2",
                    category = "Category 2",
                    area = "Area 2",
                    thumbnail = "http://url/2"
                ),
                Meal(
                    meal = "Recipe 3",
                    category = "Category 3",
                    area = "Area 3",
                    thumbnail = "http://url/3"
                ),
            ),
            listOf(
                Recipe(
                    name = "Recipe 1",
                    description = "Area 1 - Category 1",
                    thumbnail = "http://url/1"
                ),
                Recipe(
                    name = "Recipe 2",
                    description = "Area 2 - Category 2",
                    thumbnail = "http://url/2"
                ),
                Recipe(
                    name = "Recipe 3",
                    description = "Area 3 - Category 3",
                    thumbnail = "http://url/3"
                ),
            )

        )
    }

}
