/*
 * Copyright (c) 2021. Proton AG
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

package com.protonvpn.android.telemetry

import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTelemetryReporter @Inject constructor() : TelemetryReporter {
    override fun invoke(telemetryEvent: TelemetryEventData, sendImmediately: Boolean) {
        // Tracker removed
    }
}

@Reusable
class TelemetryFlowHelper @Inject constructor(
    private val scope: CoroutineScope,
    private val telemetry: TelemetryReporter
) {
    fun <T> Flow<T>.report(
        sendImmediately: Boolean = false,
        event: suspend (T) -> TelemetryEventData?
    ): Flow<T> = onEach {
        event(it)?.let { data -> telemetry(data, sendImmediately) }
    }.also { it.launchIn(scope) }

    fun event(sendImmediately: Boolean = false, event: suspend () -> TelemetryEventData?) {
        scope.launch {
            event()?.let { data -> telemetry(data, sendImmediately) }
        }
    }
}
