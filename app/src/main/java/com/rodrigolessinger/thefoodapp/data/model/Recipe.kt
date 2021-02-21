package com.rodrigolessinger.thefoodapp.data.model

data class Recipe(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: String,
    val ingredients: List<String>,
    val instructions: String,
)
