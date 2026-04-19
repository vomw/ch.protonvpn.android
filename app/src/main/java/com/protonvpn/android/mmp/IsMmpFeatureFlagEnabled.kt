/*
 * Copyright (c) 2026 Proton AG
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

package com.protonvpn.android.mmp

import com.protonvpn.android.base.data.FakeVpnFeatureFlag
import com.protonvpn.android.base.data.VpnFeatureFlag
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface IsMmpFeatureFlagEnabled : VpnFeatureFlag

@Reusable
class IsMmpFeatureFlagEnabledImpl @Inject constructor() : IsMmpFeatureFlagEnabled {
    override suspend fun invoke(): Boolean = false
    override fun observe(): Flow<Boolean> = flowOf(false)
}

class FakeIsMmpFeatureFlagEnabled(
    enabled: Boolean,
) : IsMmpFeatureFlagEnabled, FakeVpnFeatureFlag(enabled)
