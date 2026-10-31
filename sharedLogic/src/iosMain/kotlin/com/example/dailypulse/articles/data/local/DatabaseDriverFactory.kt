package com.example.dailypulse.articles.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import example.dailypulse.db.DailyPulseDatabase

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        schema = DailyPulseDatabase.Schema,
        name = "DailyPulse.Database.db"
    )
}