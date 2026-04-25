package me.proton.core.observability.domain

import me.proton.core.observability.domain.entity.ObservabilityEvent
import me.proton.core.observability.domain.metrics.ObservabilityData
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObservabilityManager @Inject constructor() {
    fun enqueue(event: ObservabilityEvent) {}
    fun enqueue(data: ObservabilityData, instant: Instant = Instant.now()) {}
}
