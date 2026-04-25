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

import android.content.Context
import com.protonvpn.android.appconfig.usecase.LargeMetricsSampler
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.proton.core.observability.domain.ObservabilityRepository
import me.proton.core.observability.domain.ObservabilityWorkerManager
import me.proton.core.observability.domain.usecase.IsObservabilityEnabled
import me.proton.core.observability.domain.usecase.ProcessObservabilityEvents
import me.proton.core.observability.domain.usecase.SendObservabilityEvents
import me.proton.core.telemetry.domain.TelemetryWorkerManager
import me.proton.core.telemetry.domain.repository.TelemetryRepository
import me.proton.core.telemetry.domain.usecase.IsTelemetryEnabled
import me.proton.core.telemetry.domain.usecase.ProcessTelemetryEvents
import me.proton.core.util.android.sentry.CustomSentryTagsProcessor
import me.proton.core.util.android.sentry.GetInstallationId
import me.proton.core.util.android.sentry.IsAccountSentryLoggingEnabled
import me.proton.core.util.android.sentry.SentryHubBuilder
import me.proton.core.util.android.sentry.project.AccountSentryHubBuilder
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.util.Optional
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrivacyStubsModule {

    @Binds
    @Singleton
    abstract fun bindGetInstallationId(impl: GetInstallationIdImpl): GetInstallationId

    @Binds
    @Singleton
    abstract fun bindIsAccountSentryLoggingEnabled(impl: IsAccountSentryLoggingEnabledImpl): IsAccountSentryLoggingEnabled

    companion object {
        @Provides
        @Singleton
        fun provideCustomSentryTagsProcessor(@ApplicationContext context: Context): CustomSentryTagsProcessor =
            CustomSentryTagsProcessor(context, null, null, null, Optional.empty<Any>())

        @Provides
        @Singleton
        fun provideSentryHubBuilder(processor: CustomSentryTagsProcessor): SentryHubBuilder =
            SentryHubBuilder(processor)

        @Provides
        @Singleton
        fun provideAccountSentryHubBuilder(
            @ApplicationContext context: Context,
            builder: SentryHubBuilder,
            getInstallationId: GetInstallationId,
            isAccountSentryLoggingEnabled: IsAccountSentryLoggingEnabled
        ): AccountSentryHubBuilder = AccountSentryHubBuilder(
            builder,
            "https://localhost".toHttpUrl(),
            context,
            getInstallationId,
            isAccountSentryLoggingEnabled
        )

        @Provides
        @Singleton
        fun provideLargeMetricsSampler(): LargeMetricsSampler = LargeMetricsSampler()

        @Provides
        @Singleton
        fun provideObservabilityRepository(): ObservabilityRepository = object : ObservabilityRepository {
            override suspend fun addEvent(event: me.proton.core.observability.domain.entity.ObservabilityEvent) {}
            override suspend fun deleteEvents(events: List<me.proton.core.observability.domain.entity.ObservabilityEvent>) {}
            override suspend fun getEventsAndSanitizeDb(maxEvents: Int?): List<me.proton.core.observability.domain.entity.ObservabilityEvent> = emptyList()
        }

        @Provides
        @Singleton
        fun provideObservabilityWorkerManager(): ObservabilityWorkerManager = object : ObservabilityWorkerManager {
            override fun enqueueOrKeep(delay: Long) {}
            override fun cancel() {}
        }

        @Provides
        @Singleton
        fun provideTelemetryRepository(): TelemetryRepository = object : TelemetryRepository {
            override suspend fun addEvent(event: me.proton.core.telemetry.domain.entity.TelemetryEvent) {}
            override suspend fun deleteAll() {}
        }

        @Provides
        @Singleton
        fun provideTelemetryWorkerManager(): TelemetryWorkerManager = object : TelemetryWorkerManager {
            override fun enqueueOrKeep(delay: Long) {}
            override fun cancel() {}
        }
        
        @Provides
        @Singleton
        fun provideIsObservabilityEnabled(): IsObservabilityEnabled = IsObservabilityEnabled()

        @Provides
        @Singleton
        fun provideIsTelemetryEnabled(): IsTelemetryEnabled = IsTelemetryEnabled()

        @Provides
        @Singleton
        fun provideSendObservabilityEvents(): SendObservabilityEvents = SendObservabilityEvents()

        @Provides
        @Singleton
        fun provideProcessObservabilityEvents(): ProcessObservabilityEvents = ProcessObservabilityEvents()

        @Provides
        @Singleton
        fun provideProcessTelemetryEvents(): ProcessTelemetryEvents = ProcessTelemetryEvents()
    }
}

@Singleton
class GetInstallationIdImpl @Inject constructor() : GetInstallationId {
    override fun invoke(): String = ""
}

@Singleton
class IsAccountSentryLoggingEnabledImpl @Inject constructor() : IsAccountSentryLoggingEnabled {
    override fun invoke(): Boolean = false
}
