/*
 * Copyright (c) 2025. Proton AG
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

import com.protonvpn.android.appconfig.usecase.LargeMetricsSampler
import com.protonvpn.android.redesign.vpn.ConnectIntent
import dagger.Reusable
import me.proton.core.observability.domain.ObservabilityManager
import javax.inject.Inject

@Reusable
class VpnErrorAndFallbackObservability @Inject constructor(
    private val observabilityManager: ObservabilityManager,
    private val largeMetricsSampler: LargeMetricsSampler,
) {

    suspend fun reportError(error: ErrorType) {
        // Tracker removed
    }

    fun reportFallback(fallback: VpnFallbackResult) {
        // Tracker removed
    }

    fun reportFallbackFailure(connectIntent: ConnectIntent, reason: SwitchServerReason) {
        // Tracker removed
    }
}
