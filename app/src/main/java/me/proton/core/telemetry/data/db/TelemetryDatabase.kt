package me.proton.core.telemetry.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

interface TelemetryDatabase {
    fun telemetryDao(): TelemetryDao

    companion object {
        val MIGRATION_0 = object : Migration(21, 22) {
            override fun migrate(db: SupportSQLiteDatabase) {}
        }
    }
}
