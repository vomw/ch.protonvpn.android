# ProtonVPN Android Project Summary

## Project Overview
- **Source**: ProtonVPN for Android.
- **Build System**: Gradle with custom Kotlin DSL plugins (`build-logic`).
- **Versioning**: Derived from Git tags and commit counts via custom Gradle tasks.
- **Flavors**: 3-dimensional flavor system (Environment, Functionality, Distribution).
- **Primary Variant**: `productionVanillaDirectRelease` (Production servers, No Google Play Services, Standard APK).

## User Preferences & Mandates
- **Privacy First**: Completely remove all trackers and telemetry (Sentry, InstallReferrer, MMP).
- **Automation**: GitHub Actions workflow for automated building, signing, and artifact delivery.
- **Artifact Naming**: The APK and artifact MUST follow the pattern: `ch.protonvpn.android-v{version}.apk`.
- **Clean CI Logs**: Prevent flooding CI logs with non-essential info (license agreements, Gradle lifecycle info). Use `-q` for Gradle.
- **Reliable Debugging**: Capture and upload build reports (`**/build/reports/`) automatically on failure.
- **Autonomous Operations**: Prefer surgical fixes and stubbing to maintain functionality while removing tracking.

## Technical Knowledge Base
- **Sentry Removal**: Fully stubbed `SentryIntegration` and `GlobalSentryLogWriter`. Removed `io.sentry` dependencies from Gradle and Manifest.
- **InstallReferrer Removal**: Removed all `com.android.installreferrer` dependencies and associated logic.
- **MMP (Mobile Measurement Partner)**: Disabled via `IsMmpFeatureFlagEnabledImpl` stub returning `false`.
- **Signing**: Uses `r0adkll/sign-android-release@v1` with `BUILD_TOOLS_VERSION: "36.0.0"` passed as an environment variable.
- **Android SDK Setup**: Explicitly use `packages: "platform-tools"` and `cmdline-tools-version: "latest"` in GitHub Actions to avoid problematic legacy `tools` package zip errors.
- **Interface Integrity**: When stubbing `IsMmpFeatureFlagEnabledImpl`, ensured it implements both `invoke(): Boolean` and `observe(): Flow<Boolean>` from the `VpnFeatureFlag` interface.
- **Logic Correction**: Fixed `TelemetryCache.kt` to use named lambda parameters (e.g., `writer`, `event`) to avoid `it` shadowing and unresolved reference errors.
- **KSP/Dagger**: Sensitive to missing imports during refactoring; always ensure `ServerListUpdater` and other core classes are imported in modified files.
