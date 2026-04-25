package me.proton.core.telemetry.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TelemetryEventEntity")
class TelemetryEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val event: String = ""
)
