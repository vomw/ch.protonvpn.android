package com.protonvpn.android.privacy

import me.proton.core.domain.entity.UserId
import me.proton.core.telemetry.domain.TelemetryWorkerManager
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration

@Singleton
class TelemetryWorkerManagerStub @Inject constructor() : TelemetryWorkerManager {
    override fun cancel() {}
    override fun enqueueAndReplace(userId: UserId, delay: Duration) {}
    override fun enqueueOrKeep(userId: UserId, delay: Duration) {}
    override fun cancel(userId: UserId) {}
}
