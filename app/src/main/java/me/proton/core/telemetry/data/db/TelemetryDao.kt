package me.proton.core.telemetry.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.proton.core.telemetry.data.entity.TelemetryEventEntity

@Dao
interface TelemetryDao {
    @Insert
    suspend fun insert(entity: TelemetryEventEntity)

    @Query("DELETE FROM TelemetryEventEntity")
    suspend fun deleteAll()
}
