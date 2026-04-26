/*
 * Copyright (c) 2026 Proton AG
 *
 * This file is part of ProtonVPN.
 *
 * ProtonVPN is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package com.protonvpn.android.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import me.proton.core.configuration.EnvironmentConfiguration
import me.proton.core.featureflag.domain.FeatureFlagOverrider
import me.proton.core.observability.domain.ObservabilityRepository
import me.proton.core.observability.domain.ObservabilityWorkerManager
import me.proton.core.observability.domain.usecase.IsObservabilityEnabled
import me.proton.core.observability.domain.usecase.ProcessObservabilityEvents
import me.proton.core.observability.domain.usecase.SendObservabilityEvents
import me.proton.core.payment.domain.repository.GoogleBillingRepository
import me.proton.core.payment.domain.usecase.FindGooglePurchaseForPaymentOrderId
import me.proton.core.payment.domain.usecase.FindUnacknowledgedGooglePurchase
import me.proton.core.telemetry.domain.TelemetryWorkerManager
import me.proton.core.telemetry.domain.repository.TelemetryLocalDataSource
import me.proton.core.telemetry.domain.repository.TelemetryRemoteDataSource
import me.proton.core.telemetry.domain.repository.TelemetryRepository
import me.proton.core.telemetry.domain.usecase.IsTelemetryEnabled
import me.proton.core.telemetry.domain.usecase.ProcessTelemetryEvents
import me.proton.core.util.android.sentry.CustomSentryTagsProcessor
import me.proton.core.util.android.sentry.GetInstallationId
import me.proton.core.util.android.sentry.IsAccountSentryLoggingEnabled
import me.proton.core.util.android.sentry.SentryHubBuilder
import me.proton.core.util.android.sentry.project.AccountSentryHubBuilder
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [
        me.proton.core.configuration.dagger.StaticEnvironmentConfigModule::class,
        PrivacyStubsModule::class
    ]
)
object HiltTestStubsModule {
    @Provides
    @Singleton
    fun provideGoogleBillingRepository(): GoogleBillingRepository<Activity> = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideFindGooglePurchaseForPaymentOrderId(): FindGooglePurchaseForPaymentOrderId = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideFindUnacknowledgedGooglePurchase(): FindUnacknowledgedGooglePurchase = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideEnvironmentConfiguration(): EnvironmentConfiguration = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideFeatureFlagOverrider(): FeatureFlagOverrider = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideGetInstallationId(): GetInstallationId = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideIsAccountSentryLoggingEnabled(): IsAccountSentryLoggingEnabled = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideCustomSentryTagsProcessor(): CustomSentryTagsProcessor = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideSentryHubBuilder(): SentryHubBuilder = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideAccountSentryHubBuilder(): AccountSentryHubBuilder = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideObservabilityRepository(): ObservabilityRepository = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideObservabilityWorkerManager(): ObservabilityWorkerManager = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideTelemetryRepository(): TelemetryRepository = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideTelemetryLocalDataSource(): TelemetryLocalDataSource = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideTelemetryRemoteDataSource(): TelemetryRemoteDataSource = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideTelemetryWorkerManager(): TelemetryWorkerManager = mockk(relaxed = true)
    
    @Provides
    @Singleton
    fun provideIsObservabilityEnabled(): IsObservabilityEnabled = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideIsTelemetryEnabled(): IsTelemetryEnabled = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideSendObservabilityEvents(): SendObservabilityEvents = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideProcessObservabilityEvents(): ProcessObservabilityEvents = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideProcessTelemetryEvents(): ProcessTelemetryEvents = mockk(relaxed = true)
}
