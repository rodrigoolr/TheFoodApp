package com.rodrigolessinger.thefoodapp.logic

fun <T> List<T>.buildGrid(numberOfColumns: Int): List<List<T>> {
    require(numberOfColumns > 0) { "numberOfColumns must be greater than 0" }

    if (this.isEmpty()) return listOf()
    if (numberOfColumns >= this.size) return listOf(this)

    return this.withIndex()
        .groupBy(
            keySelector = { it.index / numberOfColumns },
            valueTransform = { it.value }
        )
        .map { it.value }
}
