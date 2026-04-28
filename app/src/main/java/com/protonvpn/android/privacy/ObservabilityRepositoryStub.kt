package com.protonvpn.android.privacy

import me.proton.core.observability.domain.ObservabilityRepository
import me.proton.core.observability.domain.entity.ObservabilityEvent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObservabilityRepositoryStub @Inject constructor() : ObservabilityRepository {
    override suspend fun addEvent(event: ObservabilityEvent) {}
    override suspend fun deleteEvents(events: List<ObservabilityEvent>) {}
    override suspend fun getEventsAndSanitizeDb(maxEvents: Int?): List<ObservabilityEvent> = emptyList()
    override suspend fun deleteAllEvents() {}
    override suspend fun deleteEvent(event: ObservabilityEvent) {}
    override suspend fun getEventCount(): Long = 0
}
