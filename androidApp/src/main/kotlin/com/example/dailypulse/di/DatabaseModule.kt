package com.example.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.example.dailypulse.articles.data.local.DatabaseDriverFactory
import example.dailypulse.db.DailyPulseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val databaseModule = module {

    single<SqlDriver> {
        DatabaseDriverFactory(context = androidContext()).createDriver()
    }

    single<DailyPulseDatabase> {
        DailyPulseDatabase(driver = get())
    }
}