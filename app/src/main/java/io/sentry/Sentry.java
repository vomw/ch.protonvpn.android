package io.sentry;

import java.util.Map;

public class Sentry {
    public static void init(String dsn) {}
    public static void init(SentryOptions options) {}
    public static void captureException(Throwable throwable) {}
    public static void captureMessage(String message) {}
    public static void captureMessage(String message, SentryLevel level) {}
    public static void setTag(String key, String value) {}
    public static void setExtra(String key, String value) {}
    public static void setContext(String key, Map<String, Object> context) {}
}
