package com.rodrigolessinger.thefoodapp.data.mealdb

import com.rodrigolessinger.thefoodapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mealDbModule = module {
    single<MealDbService> {
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MEALDB_BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MealDbService::class.java)
    }

    single { MealDbAdapter() }
}
