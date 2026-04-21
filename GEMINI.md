# ProtonVPN Android Project Summary

## Project Overview
- **Source**: ProtonVPN for Android.
- **Build System**: Gradle with custom Kotlin DSL plugins (`build-logic`).
- **Versioning**: Derived from Git tags and commit counts via custom Gradle tasks. Overridden in CI with `v{run_number}-{YYYYMMDD-HHMM}`.
- **Flavors**: 3-dimensional flavor system (Environment, Functionality, Distribution).
- **Primary Variant**: `productionVanillaDirectRelease` (Production servers, No Google Play Services, Standard APK).

## User Preferences & Mandates
- **Privacy First**: Completely remove all trackers and telemetry (Sentry, InstallReferrer, MMP, Observability).
- **Automation**: GitHub Actions workflow for automated building, signing, and artifact delivery.
- **Artifact Naming**: The APK and artifact MUST follow the pattern: `ch.protonvpn.android-v{run_number}-{date}.apk`.
- **Clean CI Logs**: Prevent flooding CI logs with non-essential info (license agreements, Gradle lifecycle info). Use `-q` for Gradle.
- **Reliable Debugging**: Capture and upload build reports (`**/build/reports/`) automatically on failure.
- **Autonomous Operations**: Prefer surgical fixes and stubbing to maintain functionality while removing tracking.

## Technical Knowledge Base
- **Sentry Removal**: Fully stubbed `SentryIntegration` and `GlobalSentryLogWriter`. Removed `io.sentry` dependencies from Gradle and Manifest.
- **InstallReferrer Removal**: Removed all `com.android.installreferrer` dependencies and associated logic.
- **MMP (Mobile Measurement Partner)**: Disabled via `IsMmpFeatureFlagEnabledImpl` stub returning `false`.
- **Observability Removal**: Fully stubbed `VpnConnectionObservability`, `VpnErrorAndFallbackObservability`, and `AppExitObservability`.
- **Telemetry Cleanup**: Stubbed API methods (`postStats`, `postNps`, etc.) and background schedulers (`TelemetryUploadWorkerScheduler`, `SettingsSnapshotScheduler`).
- **Signing**: Uses `r0adkll/sign-android-release@v1` with `BUILD_TOOLS_VERSION: "36.0.0"` passed as an environment variable.
- **Android SDK Setup**: Explicitly use `packages: "platform-tools"` and `cmdline-tools-version: "latest"` in GitHub Actions to avoid problematic legacy `tools` package zip errors.
- **Interface Integrity**: When stubbing `IsMmpFeatureFlagEnabledImpl`, ensured it implements both `invoke(): Boolean` and `observe(): Flow<Boolean>` from the `VpnFeatureFlag` interface.
- **DoH (DNS over HTTPS)**: All app-level DNS resolution is forced through a DoH provider configured in `app/src/main/java/com/protonvpn/android/api/DohConfig.kt`. It uses `okhttp3-dnsoverhttps` with a bootstrap DNS setup (Cloudflare/Google IPs) to resolve the DoH provider itself.
- **Connection Optimization**: 
    - Reduced port scanning and ping timeouts to 2000ms.
    - Prioritized port 443 by placing it at the start of the sample list in `PrepareForConnection.kt`, which interacts with `parallelSearch` priority logic.
- **KSP/Dagger**: Sensitive to missing imports during refactoring; always ensure `ServerListUpdater` and other core classes are imported in modified files.
- **Dependency Conflicts**: Excluded `me.proton.crypto:android-golib` globally in `app/build.gradle` to resolve thousands of duplicate class conflicts with `me.proton.vpn:go-vpn-lib`. Both provide `com.proton.gopenpgp.*` and `go.*` classes; prioritized `go-vpn-lib` for VPN compatibility.
