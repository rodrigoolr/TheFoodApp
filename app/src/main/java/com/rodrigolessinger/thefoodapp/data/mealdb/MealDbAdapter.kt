package com.rodrigolessinger.thefoodapp.data.mealdb

import com.rodrigolessinger.thefoodapp.data.mealdb.model.MealList
import com.rodrigolessinger.thefoodapp.data.model.Recipe

class MealDbAdapter {
    fun mapModel(model: MealList) = model.meals.map {
        Recipe(
            id = it.id,
            name = it.meal,
            description = "${it.area} - ${it.category}",
            thumbnail = it.thumbnail,
            ingredients = listOf(
                it.measure1 to it.ingredient1,
                it.measure2 to it.ingredient2,
                it.measure3 to it.ingredient3,
                it.measure4 to it.ingredient4,
                it.measure5 to it.ingredient5,
                it.measure6 to it.ingredient6,
                it.measure7 to it.ingredient7,
                it.measure8 to it.ingredient8,
                it.measure9 to it.ingredient9,
                it.measure10 to it.ingredient10,
                it.measure11 to it.ingredient11,
                it.measure12 to it.ingredient12,
                it.measure13 to it.ingredient13,
                it.measure14 to it.ingredient14,
                it.measure15 to it.ingredient15,
                it.measure16 to it.ingredient16,
                it.measure17 to it.ingredient17,
                it.measure18 to it.ingredient18,
                it.measure19 to it.ingredient19,
                it.measure20 to it.ingredient20,
            ).filter { (measure, ingredient) ->
                !measure?.trim().isNullOrEmpty() && !ingredient?.trim().isNullOrEmpty()
            }.map { (measure, ingredient) ->
                "$measure $ingredient"
            },
            instructions = it.instructions
        )
    }
}
