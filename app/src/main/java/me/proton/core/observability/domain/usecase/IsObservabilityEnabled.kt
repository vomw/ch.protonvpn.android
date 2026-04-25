package me.proton.core.observability.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsObservabilityEnabled @Inject constructor() {
    suspend operator fun invoke(): Boolean = false
}
