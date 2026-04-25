package me.proton.core.telemetry.domain.usecase

class IsTelemetryEnabled {
    suspend operator fun invoke(): Boolean = false
}
