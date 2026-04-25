package me.proton.core.telemetry.data.db

interface TelemetryDatabase {
    fun telemetryDao(): TelemetryDao
}
