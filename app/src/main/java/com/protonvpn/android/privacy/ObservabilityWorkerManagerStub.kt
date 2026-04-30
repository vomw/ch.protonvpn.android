package com.protonvpn.android.privacy

import me.proton.core.observability.domain.ObservabilityWorkerManager
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration

@Singleton
class ObservabilityWorkerManagerStub @Inject constructor() : ObservabilityWorkerManager {
    override fun cancel() {}
}
