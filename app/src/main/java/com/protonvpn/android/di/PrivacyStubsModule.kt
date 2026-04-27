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
import com.protonvpn.android.telemetry.IsVpnTelemetryEnabled
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import me.proton.core.domain.entity.UserId
import me.proton.core.observability.domain.ObservabilityManager
import me.proton.core.observability.domain.ObservabilityRepository
import me.proton.core.observability.domain.ObservabilityWorkerManager
import me.proton.core.observability.domain.entity.ObservabilityEvent
import me.proton.core.observability.domain.usecase.IsObservabilityEnabled
import me.proton.core.observability.domain.usecase.ProcessObservabilityEvents
import me.proton.core.observability.domain.usecase.SendObservabilityEvents
import me.proton.core.telemetry.domain.TelemetryWorkerManager
import me.proton.core.telemetry.domain.entity.TelemetryEvent
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
import me.proton.core.util.kotlin.CoroutineScopeProvider
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.util.Optional
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration

@Module
@InstallIn(SingletonComponent::class)
abstract class PrivacyStubsModule {

    @Binds
    @Singleton
    abstract fun bindGetInstallationId(impl: GetInstallationIdImpl): GetInstallationId

    @Binds
    @Singleton
    abstract fun bindIsAccountSentryLoggingEnabled(impl: IsAccountSentryLoggingEnabledImpl): IsAccountSentryLoggingEnabled

    @Binds
    abstract fun bindIsTelemetryEnabled(impl: IsVpnTelemetryEnabled): IsTelemetryEnabled

    @Binds
    @Singleton
    abstract fun bindCoroutineScopeProvider(impl: CoroutineScopeProviderImpl): CoroutineScopeProvider

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
            "https://localhost".to_http_url(),
            context,
            getInstallationId,
            isAccountSentryLoggingEnabled
        )

        private fun String.to_http_url() = toHttpUrl()

        @Provides
        @Singleton
        fun provideLargeMetricsSampler(): LargeMetricsSampler = LargeMetricsSampler()

        @Provides
        @Singleton
        fun provideObservabilityRepository(): ObservabilityRepository = object : ObservabilityRepository {
            override suspend fun addEvent(event: ObservabilityEvent) {}
            override suspend fun deleteEvents(events: List<ObservabilityEvent>) {}
            override suspend fun getEventsAndSanitizeDb(maxEvents: Int?): List<ObservabilityEvent> = emptyList()
            override suspend fun deleteAllEvents() {}
            override suspend fun deleteEvent(event: ObservabilityEvent) {}
            override suspend fun getEventCount(): Long = 0
        }

        @Provides
        @Singleton
        fun provideObservabilityWorkerManager(): ObservabilityWorkerManager = object : ObservabilityWorkerManager {
            override fun enqueueOrKeep(delay: Long) {}
            override fun cancel() {}
            override fun enqueueOrKeep(delay: Duration) {}
        }

        @Provides
        @Singleton
        fun provideTelemetryRepository(): TelemetryRepository = object : TelemetryRepository {
            override suspend fun addEvent(event: TelemetryEvent) {}
            override suspend fun deleteAll() {}
            override suspend fun addEvent(userId: UserId?, event: TelemetryEvent) {}
            override suspend fun deleteAllEvents(userId: UserId?) {}
            override suspend fun deleteEvents(userId: UserId?, events: List<TelemetryEvent>) {}
            override suspend fun getEvents(userId: UserId?, limit: Int): List<TelemetryEvent> = emptyList()
            override suspend fun sendEvents(userId: UserId?, events: List<TelemetryEvent>) {}
        }

        @Provides
        @Singleton
        fun provideTelemetryLocalDataSource(): TelemetryLocalDataSource = object : TelemetryLocalDataSource {
            override suspend fun addEvent(userId: UserId, event: TelemetryEvent) {}
            override suspend fun deleteEvents(userId: UserId, events: List<TelemetryEvent>) {}
            override suspend fun getEvents(userId: UserId, maxEvents: Int?): List<TelemetryEvent> = emptyList()
            override suspend fun addEvent(userId: UserId?, event: TelemetryEvent) {}
            override suspend fun deleteAllEvents(userId: UserId?) {}
            override suspend fun deleteEvents(userId: UserId?, events: List<TelemetryEvent>) {}
            override suspend fun getEvents(userId: UserId?, limit: Int): List<TelemetryEvent> = emptyList()
        }

        @Provides
        @Singleton
        fun provideTelemetryRemoteDataSource(): TelemetryRemoteDataSource = object : TelemetryRemoteDataSource {
            override suspend fun uploadEvents(userId: UserId, events: List<TelemetryEvent>): Result<Unit> = Result.success(Unit)
            override suspend fun sendEvents(userId: UserId?, events: List<TelemetryEvent>) {}
        }

        @Provides
        @Singleton
        fun provideTelemetryWorkerManager(): TelemetryWorkerManager = object : TelemetryWorkerManager {
            override fun enqueueOrKeep(delay: Long) {}
            override fun cancel() {}
            override fun enqueueAndReplace(userId: UserId, delay: Long) {}
            override fun enqueueAndReplace(userId: UserId?, delay: Duration) {}
            override fun enqueueOrKeep(userId: UserId?, delay: Duration) {}
            override fun cancel(userId: UserId?) {}
        }
        
        @Provides
        @Singleton
        fun provideIsObservabilityEnabled(): IsObservabilityEnabled = object : IsObservabilityEnabled() {
            override suspend fun invoke(): Boolean = false
        }

        @Provides
        @Singleton
        fun provideSendObservabilityEvents(): SendObservabilityEvents = object : SendObservabilityEvents() {
            override suspend fun invoke(events: List<ObservabilityEvent>) {}
        }

        @Provides
        @Singleton
        fun provideObservabilityManager(
            isObservabilityEnabled: IsObservabilityEnabled,
            repository: ObservabilityRepository,
            scopeProvider: CoroutineScopeProvider,
            workerManager: ObservabilityWorkerManager
        ): ObservabilityManager = ObservabilityManager(isObservabilityEnabled, repository, scopeProvider, workerManager)

        @Provides
        @Singleton
        fun provideProcessObservabilityEvents(
            isObservabilityEnabled: IsObservabilityEnabled,
            repository: ObservabilityRepository,
            sendEvents: SendObservabilityEvents
        ): ProcessObservabilityEvents = ProcessObservabilityEvents(isObservabilityEnabled, repository, sendEvents)

        @Provides
        @Singleton
        fun provideProcessTelemetryEvents(
            isTelemetryEnabled: IsTelemetryEnabled,
            repository: TelemetryRepository
        ): ProcessTelemetryEvents = ProcessTelemetryEvents(isTelemetryEnabled, repository)
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

@Singleton
class CoroutineScopeProviderImpl @Inject constructor() : CoroutineScopeProvider {
    override fun get(dispatcher: Any?): CoroutineScope = MainScope()
}
