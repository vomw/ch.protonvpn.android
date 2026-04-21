package me.proton.core.util.android.sentry.project;

import android.content.Context;
import okhttp3.HttpUrl;
import me.proton.core.util.android.sentry.SentryHub;
import me.proton.core.util.android.sentry.SentryHubBuilder;
import me.proton.core.util.android.sentry.GetInstallationId;
import me.proton.core.util.android.sentry.IsAccountSentryLoggingEnabled;
import kotlin.jvm.functions.Function1;

public class AccountSentryHubBuilder {
    public AccountSentryHubBuilder(SentryHubBuilder builder, HttpUrl url, Context context, GetInstallationId getInstallationId, IsAccountSentryLoggingEnabled isAccountSentryLoggingEnabled) {}
    public SentryHub invoke(String dsn, String release, Function1 configuration) {
        return new SentryHub();
    }
}
