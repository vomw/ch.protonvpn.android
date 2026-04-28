package com.protonvpn.android.privacy

import me.proton.core.observability.domain.entity.ObservabilityEvent
import me.proton.core.observability.domain.usecase.SendObservabilityEvents
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SendObservabilityEventsStub @Inject constructor() : SendObservabilityEvents() {
    override suspend fun invoke(events: List<ObservabilityEvent>) {}
}
