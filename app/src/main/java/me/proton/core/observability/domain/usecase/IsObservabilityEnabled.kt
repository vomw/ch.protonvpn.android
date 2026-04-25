package me.proton.core.observability.domain.usecase

class IsObservabilityEnabled {
    suspend operator fun invoke(): Boolean = false
}
