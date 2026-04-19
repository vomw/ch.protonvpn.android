/*
 * Copyright (c) 2019 Proton AG
 *
 * This file is part of ProtonVPN.
 *
 * ProtonVPN is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ProtonVPN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ProtonVPN.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.protonvpn.android.vpn

import com.protonvpn.android.GoLangCrashLogger
import com.protonvpn.android.concurrency.VpnDispatcherProvider
import com.protonvpn.android.di.ElapsedRealtimeClock
import com.protonvpn.android.logging.LogCategory
import com.protonvpn.android.logging.LogLevel
import com.protonvpn.android.logging.ProtonLogger
import com.protonvpn.android.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG_MESSAGE_SEPARATOR = ": " // This is what logcat uses.

/**
 * Capture logcat messages from VPN libraries and the secondary process and log them to ProtonLogger.
 */
@Singleton
class LogcatLogCapture @Inject constructor(
    mainScope: CoroutineScope,
    dispatcherProvider: VpnDispatcherProvider,
    private val goLangCrashLogger: dagger.Lazy<GoLangCrashLogger>,
    @ElapsedRealtimeClock private val monoClock: () -> Long
) {

    init {
        mainScope.launch(dispatcherProvider.infiniteIo) {
            captureCharonWireguardAndGoLogs()
        }
    }

    private suspend fun captureCharonWireguardAndGoLogs() {
        val lastCommandRunMonoTimestamp: MutableMap<String, Long> = HashMap()

        while (true) {
            try {
                // Charon is in a different process, to capture its logs we need to use logcat.
                // Logcat filter syntax: TAG:LEVEL *:S (where S means silent, i.e. suppress others)
                val process = Runtime.getRuntime().exec(
                    arrayOf(
                        "logcat",
                        "-v", "tag",
                        "charon:V",
                        "WireGuard/WireGuardGoBackend/VPN:V",
                        "WireGuard/WireGuardGoBackend/GoLog:V",
                        "GoLog:V",
                        "*:S"
                    )
                )

                BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
                    while (true) {
                        val line = reader.readLine() ?: break
                        val parts = line.split(TAG_MESSAGE_SEPARATOR, limit = 2)
                        if (parts.size == 2) {
                            val tag = parts[0].trim()
                            val msg = parts[1]
                            val category = when (tag) {
                                "charon" -> LogCategory.CONN
                                "WireGuard/WireGuardGoBackend/VPN" -> LogCategory.CONN_WIREGUARD
                                "WireGuard/WireGuardGoBackend/GoLog", "GoLog" -> LogCategory.CONN_WIREGUARD
                                else -> LogCategory.APP
                            }
                            ProtonLogger.logCustom(LogLevel.INFO, category, msg)

                            if (category == LogCategory.CONN_WIREGUARD) {
                                goLangCrashLogger.get().onErrorLine(msg)
                            }
                        }
                    }
                }
            } catch (e: IOException) {
                ProtonLogger.logCustom(LogCategory.APP, "LogcatLogCapture: exec failed: ${e.message}")
            }
            delay(1000)
        }
    }
}
