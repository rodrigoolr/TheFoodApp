package com.rodrigolessinger.thefoodapp.data.mealdb

import com.rodrigolessinger.thefoodapp.data.mealdb.model.MealList
import retrofit2.http.GET
import retrofit2.http.Path

interface MealDbService {
    @GET("json/v2/{api_key}/latest.php")
    suspend fun getLatestRecipes(@Path("api_key") apiKey: String): MealList
}
