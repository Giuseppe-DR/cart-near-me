package com.cartnearme.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.cartnearme.db.CartDatabase
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val databasePath = File("cart.db")
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:${databasePath.absolutePath}")
        
        // Create schema if database doesn't exist
        if (!databasePath.exists()) {
            CartDatabase.Schema.create(driver)
        }
        
        return driver
    }
}
