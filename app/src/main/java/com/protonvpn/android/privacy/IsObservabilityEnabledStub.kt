package com.protonvpn.android.privacy

import me.proton.core.observability.domain.usecase.IsObservabilityEnabled
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsObservabilityEnabledStub @Inject constructor() : IsObservabilityEnabled {
    override suspend fun invoke(): Boolean = false
}
