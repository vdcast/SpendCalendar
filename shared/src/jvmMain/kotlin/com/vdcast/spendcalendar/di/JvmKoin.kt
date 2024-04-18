package com.vdcast.spendcalendar.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import com.vdcast.spendcalendar.db.AppDb
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File
import java.util.prefs.Preferences

actual val platformModule: Module = module {
    single<Settings> { PreferencesSettings(Preferences.userRoot()) }
    single<SqlDriver> {

////        val driver = JdbcSqliteDriver("jdbc:sqlite:AppDb.db")
//        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
//        AppDb.Schema.create(driver)
//        driver

        val dbFilePath = "AppDb.db"
        val dbFile = File(dbFilePath)
        val isNewDatabase = !dbFile.exists()

        val driver = JdbcSqliteDriver("jdbc:sqlite:$dbFilePath")

        if (isNewDatabase) {
            AppDb.Schema.create(driver) // Створення схеми, якщо це нова база
        }

        driver
    }
}
