package com.example.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.example.dailypulse.articles.data.local.DatabaseDriverFactory
import example.dailypulse.db.DailyPulseDatabase
import org.koin.dsl.module

val databaseModule = module {

    single<SqlDriver> {
        DatabaseDriverFactory().createDriver()
    }

    single<DailyPulseDatabase> {
        DailyPulseDatabase(driver = get())
    }
}