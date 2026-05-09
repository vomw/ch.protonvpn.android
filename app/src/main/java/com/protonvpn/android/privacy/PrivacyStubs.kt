package com.protonvpn.android.privacy

import me.proton.core.domain.entity.UserId
import me.proton.core.observability.domain.ObservabilityRepository
import me.proton.core.observability.domain.ObservabilityWorkerManager
import me.proton.core.observability.domain.entity.ObservabilityEvent
import me.proton.core.telemetry.domain.TelemetryWorkerManager
import me.proton.core.telemetry.domain.entity.TelemetryEvent
import me.proton.core.telemetry.domain.repository.TelemetryLocalDataSource
import me.proton.core.telemetry.domain.repository.TelemetryRemoteDataSource
import me.proton.core.telemetry.domain.repository.TelemetryRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration

@Singleton
class ObservabilityRepositoryStub @Inject constructor() : ObservabilityRepository {
    override suspend fun addEvent(event: ObservabilityEvent) {}
    override suspend fun deleteEvents(events: List<ObservabilityEvent>) {}
    override suspend fun getEventsAndSanitizeDb(maxEvents: Int?): List<ObservabilityEvent> = emptyList()
    override suspend fun deleteAllEvents() {}
    override suspend fun deleteEvent(event: ObservabilityEvent) {}
    override suspend fun getEventCount(): Long = 0
}

@Singleton
class ObservabilityWorkerManagerStub @Inject constructor() : ObservabilityWorkerManager {
    override fun cancel() {}
    override fun enqueueOrKeep(delay: Duration) {}
}

@Singleton
class TelemetryRepositoryStub @Inject constructor() : TelemetryRepository {
    override suspend fun addEvent(userId: UserId?, event: TelemetryEvent) {}
    override suspend fun deleteAllEvents(userId: UserId?) { }
    override suspend fun deleteEvents(userId: UserId?, events: List<TelemetryEvent>) {}
    override suspend fun getEvents(userId: UserId?, limit: Int): List<TelemetryEvent> = emptyList()
    override suspend fun sendEvents(userId: UserId?, events: List<TelemetryEvent>) {}
}

@Singleton
class TelemetryLocalDataSourceStub @Inject constructor() : TelemetryLocalDataSource {
    override suspend fun addEvent(userId: UserId?, event: TelemetryEvent) {}
    override suspend fun deleteAllEvents(userId: UserId?) {}
    override suspend fun deleteEvents(userId: UserId?, events: List<TelemetryEvent>) {}
    override suspend fun getEvents(userId: UserId?, limit: Int): List<TelemetryEvent> = emptyList()
}

@Singleton
class TelemetryRemoteDataSourceStub @Inject constructor() : TelemetryRemoteDataSource {
    override suspend fun sendEvents(userId: UserId?, events: List<TelemetryEvent>) {}
}

@Singleton
class TelemetryWorkerManagerStub @Inject constructor() : TelemetryWorkerManager {
    override fun cancel(userId: UserId?) {}
    override fun enqueueAndReplace(userId: UserId?, delay: Duration) {}
    override fun enqueueOrKeep(userId: UserId?, delay: Duration) {}
}
