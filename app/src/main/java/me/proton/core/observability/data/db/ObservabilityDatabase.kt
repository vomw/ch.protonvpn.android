package me.proton.core.observability.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

interface ObservabilityDatabase {
    fun observabilityDao(): ObservabilityDao

    companion object {
        val MIGRATION_0 = object : Migration(11, 12) {
            override fun migrate(db: SupportSQLiteDatabase) {}
        }
    }
}
