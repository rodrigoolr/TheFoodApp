package com.rodrigolessinger.thefoodapp.data.mealdb

import com.rodrigolessinger.thefoodapp.data.mealdb.model.MealList
import com.rodrigolessinger.thefoodapp.data.model.Recipe

class MealDbAdapter {
    fun mapModel(model: MealList) = model.meals.map {
        Recipe(
            name = it.meal,
            description = "${it.area} - ${it.category}",
            thumbnail = it.thumbnail
        )
    }
}
