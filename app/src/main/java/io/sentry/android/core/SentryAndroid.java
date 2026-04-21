package io.sentry.android.core;

import android.content.Context;
import io.sentry.Sentry;

public class SentryAndroid {
    public interface OptionsConfiguration<T> {
        void configure(T options);
    }
    public static void init(Context context) {}
    public static void init(Context context, OptionsConfiguration<SentryAndroidOptions> configuration) {}
}
