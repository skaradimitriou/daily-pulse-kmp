package com.example.dailypulse

import android.app.Application
import com.example.dailypulse.articles.di.articlesModule
import com.example.dailypulse.di.databaseModule
import com.example.dailypulse.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = articlesModule + viewModelModule + databaseModule
        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}