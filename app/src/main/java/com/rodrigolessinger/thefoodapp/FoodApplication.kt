package com.rodrigolessinger.thefoodapp

import android.app.Application
import com.rodrigolessinger.thefoodapp.data.dataModule
import com.rodrigolessinger.thefoodapp.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FoodApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FoodApplication)
            modules(dataModule + presentationModule)
        }
    }
}
