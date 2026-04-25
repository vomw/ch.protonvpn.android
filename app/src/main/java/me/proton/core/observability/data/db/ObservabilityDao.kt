package me.proton.core.observability.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.proton.core.observability.data.entity.ObservabilityEventEntity

@Dao
interface ObservabilityDao {
    @Insert
    suspend fun insert(entity: ObservabilityEventEntity)

    @Query("DELETE FROM ObservabilityEventEntity")
    suspend fun deleteAll()
}
