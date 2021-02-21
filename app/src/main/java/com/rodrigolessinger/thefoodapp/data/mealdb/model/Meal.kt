package com.rodrigolessinger.thefoodapp.data.mealdb.model

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("strMeal") val meal: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strArea") val area: String,
    @SerializedName("strMealThumb") val thumbnail: String,
)
