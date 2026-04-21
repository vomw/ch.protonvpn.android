package me.proton.core.util.android.sentry;

import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import kotlin.jvm.functions.Function1;
import java.io.File;
import java.util.List;
import java.util.Set;

public class SentryHubBuilder {
    public SentryHub invoke(String dsn, Set<String> beforeSendCallbacks, Set<String> beforeBreadcrumbCallbacks, File cacheDir, String release, List<String> extraContexts, String environment, SentryLevel serverLevel, SentryLevel clientLevel, String dist, boolean debug, Function1<SentryOptions, Unit> configuration) {
        return new SentryHub();
    }
}

class Unit {}
