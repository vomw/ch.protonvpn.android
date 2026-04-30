package com.protonvpn.android.privacy

import me.proton.core.domain.entity.UserId
import me.proton.core.telemetry.domain.entity.TelemetryEvent
import me.proton.core.telemetry.domain.repository.TelemetryRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TelemetryRemoteDataSourceStub @Inject constructor() : TelemetryRemoteDataSource {
}
