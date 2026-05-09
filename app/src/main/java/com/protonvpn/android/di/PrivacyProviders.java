package com.protonvpn.android.di;

import me.proton.core.observability.domain.ObservabilityManager;
import me.proton.core.observability.domain.ObservabilityRepository;
import me.proton.core.observability.domain.ObservabilityWorkerManager;
import me.proton.core.observability.domain.usecase.IsObservabilityEnabled;
import me.proton.core.observability.domain.usecase.ProcessObservabilityEvents;
import me.proton.core.observability.domain.usecase.SendObservabilityEvents;
import me.proton.core.telemetry.domain.TelemetryWorkerManager;
import me.proton.core.telemetry.domain.repository.TelemetryLocalDataSource;
import me.proton.core.telemetry.domain.repository.TelemetryRemoteDataSource;
import me.proton.core.telemetry.domain.repository.TelemetryRepository;
import me.proton.core.telemetry.domain.usecase.IsTelemetryEnabled;
import me.proton.core.telemetry.domain.usecase.ProcessTelemetryEvents;
import me.proton.core.util.kotlin.CoroutineScopeProvider;
import com.protonvpn.android.privacy.ObservabilityRepositoryStub;
import com.protonvpn.android.privacy.ObservabilityWorkerManagerStub;
import com.protonvpn.android.privacy.TelemetryRepositoryStub;
import com.protonvpn.android.privacy.TelemetryLocalDataSourceStub;
import com.protonvpn.android.privacy.TelemetryRemoteDataSourceStub;
import com.protonvpn.android.privacy.TelemetryWorkerManagerStub;

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
        android.util.Log.d("ProtonVpn", "Providing ObservabilityManager");
        return new ObservabilityManager(isObservabilityEnabled, repository, scopeProvider, workerManager);
    }

    @Provides
    @Singleton
    public static ProcessObservabilityEvents provideProcessObservabilityEvents(
            IsObservabilityEnabled isObservabilityEnabled,
            ObservabilityRepository repository,
            SendObservabilityEvents sendEvents
    ) {
        android.util.Log.d("ProtonVpn", "Providing ProcessObservabilityEvents");
        return new ProcessObservabilityEvents(isObservabilityEnabled, repository, sendEvents);
    }

    @Provides
    @Singleton
    public static ProcessTelemetryEvents provideProcessTelemetryEvents(
            IsTelemetryEnabled isTelemetryEnabled,
            TelemetryRepository repository
    ) {
        android.util.Log.d("ProtonVpn", "Providing ProcessTelemetryEvents");
        return new ProcessTelemetryEvents(isTelemetryEnabled, repository);
    }

    @Provides
    @Singleton
    public static ObservabilityWorkerManager provideObservabilityWorkerManager(ObservabilityWorkerManagerStub impl) {
        return impl;
    }

    @Provides
    @Singleton
    public static TelemetryWorkerManager provideTelemetryWorkerManager(TelemetryWorkerManagerStub impl) {
        return impl;
    }

    @Provides
    @Singleton
    public static ObservabilityRepository provideObservabilityRepository(ObservabilityRepositoryStub impl) {
        return impl;
    }

    @Provides
    @Singleton
    public static TelemetryRepository provideTelemetryRepository(TelemetryRepositoryStub impl) {
        return impl;
    }

    @Provides
    @Singleton
    public static TelemetryLocalDataSource provideTelemetryLocalDataSource(TelemetryLocalDataSourceStub impl) {
        return impl;
    }

    @Provides
    @Singleton
    public static TelemetryRemoteDataSource provideTelemetryRemoteDataSource(TelemetryRemoteDataSourceStub impl) {
        return impl;
    }
}
