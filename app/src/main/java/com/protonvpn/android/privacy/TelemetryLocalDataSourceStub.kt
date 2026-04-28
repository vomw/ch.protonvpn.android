package com.protonvpn.android.privacy

import me.proton.core.domain.entity.UserId
import me.proton.core.telemetry.domain.entity.TelemetryEvent
import me.proton.core.telemetry.domain.repository.TelemetryLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TelemetryLocalDataSourceStub @Inject constructor() : TelemetryLocalDataSource {
    override suspend fun addEvent(userId: UserId, event: TelemetryEvent) {}
    override suspend fun deleteEvents(userId: UserId, events: List<TelemetryEvent>) {}
    override suspend fun getEvents(userId: UserId, maxEvents: Int?): List<TelemetryEvent> = emptyList()
    override suspend fun addEvent(userId: UserId?, event: TelemetryEvent) {}
    override suspend fun deleteAllEvents(userId: UserId?) {}
    override suspend fun deleteEvents(userId: UserId?, events: List<TelemetryEvent>) {}
    override suspend fun getEvents(userId: UserId?, limit: Int): List<TelemetryEvent> = emptyList()
}
