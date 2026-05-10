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
}

@Singleton
class ObservabilityWorkerManagerStub @Inject constructor() : ObservabilityWorkerManager {
}

@Singleton
class TelemetryRepositoryStub @Inject constructor() : TelemetryRepository {
}

@Singleton
class TelemetryLocalDataSourceStub @Inject constructor() : TelemetryLocalDataSource {
}

@Singleton
class TelemetryRemoteDataSourceStub @Inject constructor() : TelemetryRemoteDataSource {
}

@Singleton
class TelemetryWorkerManagerStub @Inject constructor() : TelemetryWorkerManager {
}
