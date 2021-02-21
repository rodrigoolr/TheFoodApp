package com.rodrigolessinger.thefoodapp.presentation.list.ui

import com.rodrigolessinger.thefoodapp.data.model.Recipe

class GridBuilder {

    fun build(recipes: List<Recipe>, numberOfColumns: Int): List<List<Recipe>> {
        assert(numberOfColumns > 0)

        if (recipes.isEmpty()) return listOf()
        if (numberOfColumns >= recipes.size) return listOf(recipes)

        return recipes.withIndex()
            .groupBy(
                keySelector = { it.index / numberOfColumns },
                valueTransform = { it.value }
            )
            .map { it.value }
    }

}
