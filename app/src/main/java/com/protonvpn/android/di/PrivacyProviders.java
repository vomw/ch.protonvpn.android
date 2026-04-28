package com.protonvpn.android.di;

import me.proton.core.observability.domain.ObservabilityManager;
import me.proton.core.observability.domain.ObservabilityRepository;
import me.proton.core.observability.domain.ObservabilityWorkerManager;
import me.proton.core.observability.domain.usecase.IsObservabilityEnabled;
import me.proton.core.observability.domain.usecase.ProcessObservabilityEvents;
import me.proton.core.observability.domain.usecase.SendObservabilityEvents;
import me.proton.core.telemetry.domain.repository.TelemetryRepository;
import me.proton.core.telemetry.domain.usecase.IsTelemetryEnabled;
import me.proton.core.telemetry.domain.usecase.ProcessTelemetryEvents;
import me.proton.core.util.kotlin.CoroutineScopeProvider;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class PrivacyProviders {
    @Provides
    @Singleton
    public static ObservabilityManager provideObservabilityManager(
            IsObservabilityEnabled isObservabilityEnabled,
            ObservabilityRepository repository,
            CoroutineScopeProvider scopeProvider,
            ObservabilityWorkerManager workerManager
    ) {
        return new ObservabilityManager(isObservabilityEnabled, repository, scopeProvider, workerManager);
    }

    @Provides
    @Singleton
    public static ProcessObservabilityEvents provideProcessObservabilityEvents(
            IsObservabilityEnabled isObservabilityEnabled,
            ObservabilityRepository repository,
            SendObservabilityEvents sendEvents
    ) {
        return new ProcessObservabilityEvents(isObservabilityEnabled, repository, sendEvents);
    }

    @Provides
    @Singleton
    public static ProcessTelemetryEvents provideProcessTelemetryEvents(
            IsTelemetryEnabled isTelemetryEnabled,
            TelemetryRepository repository
    ) {
        return new ProcessTelemetryEvents(isTelemetryEnabled, repository);
    }
}
