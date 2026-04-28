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
import com.protonvpn.android.privacy.*
import com.protonvpn.android.telemetry.IsVpnTelemetryEnabled
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import me.proton.core.observability.domain.ObservabilityManager
import me.proton.core.observability.domain.ObservabilityRepository
import me.proton.core.observability.domain.ObservabilityWorkerManager
import me.proton.core.observability.domain.usecase.IsObservabilityEnabled
import me.proton.core.observability.domain.usecase.ProcessObservabilityEvents
import me.proton.core.observability.domain.usecase.SendObservabilityEvents
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
import me.proton.core.util.kotlin.CoroutineScopeProvider
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

    @Binds
    abstract fun bindIsTelemetryEnabled(impl: IsVpnTelemetryEnabled): IsTelemetryEnabled

    @Binds
    @Singleton
    abstract fun bindCoroutineScopeProvider(impl: CoroutineScopeProviderImpl): CoroutineScopeProvider

    @Binds
    @Singleton
    abstract fun bindObservabilityRepository(impl: ObservabilityRepositoryStub): ObservabilityRepository

    @Binds
    @Singleton
    abstract fun bindObservabilityWorkerManager(impl: ObservabilityWorkerManagerStub): ObservabilityWorkerManager

    @Binds
    @Singleton
    abstract fun bindTelemetryRepository(impl: TelemetryRepositoryStub): TelemetryRepository

    @Binds
    @Singleton
    abstract fun bindTelemetryLocalDataSource(impl: TelemetryLocalDataSourceStub): TelemetryLocalDataSource

    @Binds
    @Singleton
    abstract fun bindTelemetryRemoteDataSource(impl: TelemetryRemoteDataSourceStub): TelemetryRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindTelemetryWorkerManager(impl: TelemetryWorkerManagerStub): TelemetryWorkerManager

    @Binds
    @Singleton
    abstract fun bindIsObservabilityEnabled(impl: IsObservabilityEnabledStub): IsObservabilityEnabled

    @Binds
    @Singleton
    abstract fun bindSendObservabilityEvents(impl: SendObservabilityEventsStub): SendObservabilityEvents

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
