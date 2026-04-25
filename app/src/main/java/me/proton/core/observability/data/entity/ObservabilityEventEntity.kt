package me.proton.core.observability.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ObservabilityEventEntity")
class ObservabilityEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val event: String = ""
)
