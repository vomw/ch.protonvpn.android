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

import java.util.Collections;
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

    private static Object createNoOpProxy(Class<?> clazz) {
        return java.lang.reflect.Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                (proxy, method, args) -> {
                    Class<?> returnType = method.getReturnType();
                    if (returnType.equals(void.class)) return null;
                    String typeName = returnType.getName();
                    if (typeName.equals("kotlin.Unit")) return kotlin.Unit.INSTANCE;
                    if (returnType.equals(boolean.class) || returnType.equals(Boolean.class)) return false;
                    if (returnType.equals(long.class) || returnType.equals(Long.class)) return 0L;
                    if (returnType.equals(int.class) || returnType.equals(Integer.class)) return 0;
                    if (returnType.equals(java.util.List.class)) return Collections.emptyList();
                    return null;
                }
        );
    }

    @Provides
    @Singleton
    public static ObservabilityWorkerManager provideObservabilityWorkerManager() {
        return (ObservabilityWorkerManager) createNoOpProxy(ObservabilityWorkerManager.class);
    }

    @Provides
    @Singleton
    public static TelemetryWorkerManager provideTelemetryWorkerManager() {
        return (TelemetryWorkerManager) createNoOpProxy(TelemetryWorkerManager.class);
    }

    @Provides
    @Singleton
    public static ObservabilityRepository provideObservabilityRepository() {
        return (ObservabilityRepository) createNoOpProxy(ObservabilityRepository.class);
    }

    @Provides
    @Singleton
    public static TelemetryRepository provideTelemetryRepository() {
        return (TelemetryRepository) createNoOpProxy(TelemetryRepository.class);
    }

    @Provides
    @Singleton
    public static TelemetryLocalDataSource provideTelemetryLocalDataSource() {
        return (TelemetryLocalDataSource) createNoOpProxy(TelemetryLocalDataSource.class);
    }

    @Provides
    @Singleton
    public static TelemetryRemoteDataSource provideTelemetryRemoteDataSource() {
        return (TelemetryRemoteDataSource) createNoOpProxy(TelemetryRemoteDataSource.class);
    }
}
