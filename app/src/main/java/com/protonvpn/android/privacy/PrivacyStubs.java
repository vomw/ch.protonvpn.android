package com.protonvpn.android.privacy;

import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import kotlin.coroutines.Continuation;
import me.proton.core.domain.entity.UserId;
import me.proton.core.observability.domain.ObservabilityRepository;
import me.proton.core.observability.domain.ObservabilityWorkerManager;
import me.proton.core.observability.domain.entity.ObservabilityEvent;
import me.proton.core.telemetry.domain.TelemetryWorkerManager;
import me.proton.core.telemetry.domain.entity.TelemetryEvent;
import me.proton.core.telemetry.domain.repository.TelemetryLocalDataSource;
import me.proton.core.telemetry.domain.repository.TelemetryRemoteDataSource;
import me.proton.core.telemetry.domain.repository.TelemetryRepository;
import kotlin.time.Duration;

public class PrivacyStubs {

    @Singleton
    public static class ObservabilityRepositoryStub implements ObservabilityRepository {
        @Inject public ObservabilityRepositoryStub() {}
        @Override public Object addEvent(ObservabilityEvent event, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object deleteEvents(List<ObservabilityEvent> events, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object getEventsAndSanitizeDb(Integer maxEvents, Continuation<? super List<ObservabilityEvent>> continuation) { return Collections.emptyList(); }
        @Override public Object deleteAllEvents(Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object deleteEvent(ObservabilityEvent event, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object getEventCount(Continuation<? super Long> continuation) { return 0L; }
    }

    @Singleton
    public static class ObservabilityWorkerManagerStub implements ObservabilityWorkerManager {
        @Inject public ObservabilityWorkerManagerStub() {}
        @Override public void cancel() {}
        @Override public void enqueueOrKeep(long delay) {}
        @Override public void enqueueOrKeep(Duration delay) {}
    }

    @Singleton
    public static class TelemetryRepositoryStub implements TelemetryRepository {
        @Inject public TelemetryRepositoryStub() {}
        @Override public Object addEvent(TelemetryEvent event, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object deleteAll(Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object addEvent(UserId userId, TelemetryEvent event, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object deleteAllEvents(UserId userId, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object deleteEvents(UserId userId, List<TelemetryEvent> events, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object getEvents(UserId userId, int limit, Continuation<? super List<TelemetryEvent>> continuation) { return Collections.emptyList(); }
        @Override public Object sendEvents(UserId userId, List<TelemetryEvent>, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
    }

    @Singleton
    public static class TelemetryLocalDataSourceStub implements TelemetryLocalDataSource {
        @Inject public TelemetryLocalDataSourceStub() {}
        @Override public Object addEvent(UserId userId, TelemetryEvent event, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object deleteAllEvents(UserId userId, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object deleteEvents(UserId userId, List<TelemetryEvent> events, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object getEvents(UserId userId, int limit, Continuation<? super List<TelemetryEvent>> continuation) { return Collections.emptyList(); }
        @Override public Object addEvent(UserId userId, TelemetryEvent event, boolean overwrite, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
        @Override public Object getEvents(UserId userId, Integer maxEvents, Continuation<? super List<TelemetryEvent>> continuation) { return Collections.emptyList(); }
    }

    @Singleton
    public static class TelemetryRemoteDataSourceStub implements TelemetryRemoteDataSource {
        @Inject public TelemetryRemoteDataSourceStub() {}
        @Override public Object uploadEvents(UserId userId, List<TelemetryEvent> events, Continuation<? super me.proton.core.util.kotlin.Result<kotlin.Unit>> continuation) { return null; }
        @Override public Object sendEvents(UserId userId, List<TelemetryEvent> events, Continuation<? super kotlin.Unit> continuation) { return kotlin.Unit.INSTANCE; }
    }

    @Singleton
    public static class TelemetryWorkerManagerStub implements TelemetryWorkerManager {
        @Inject public TelemetryWorkerManagerStub() {}
        @Override public void cancel() {}
        @Override public void cancel(UserId userId) {}
        @Override public void enqueueAndReplace(UserId userId, long delay) {}
        @Override public void enqueueAndReplace(UserId userId, Duration delay) {}
        @Override public void enqueueOrKeep(long delay) {}
        @Override public void enqueueOrKeep(UserId userId, Duration delay) {}
        @Override public void enqueueOrKeep(Duration delay) {}
    }
}
