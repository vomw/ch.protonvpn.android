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

package com.protonvpn.android.utils

import android.app.Application
import java.util.UUID

/**
 * Stubbed out SentryIntegration to remove trackers while maintaining app functionality.
 */
object SentryIntegration {

    private const val SENTRY_INSTALLATION_ID_KEY = "sentry_installation_id"
    private const val SENTRY_ENABLED_KEY = "sentry_is_enabled"

    @JvmStatic
    fun getInstallationId(): String =
        Storage.getString(SENTRY_INSTALLATION_ID_KEY, null)
            ?: UUID.randomUUID().toString().also {
                Storage.saveString(SENTRY_INSTALLATION_ID_KEY, it)
            }

    @JvmStatic
    fun initSentry(app: Application) {
        // Tracker removed
    }

    fun setEnabled(isEnabled: Boolean) {
        Storage.saveBoolean(SENTRY_ENABLED_KEY, false)
    }

    fun isEnabled() = false

    private fun initSentry() {
        // Tracker removed
    }

    fun initAccountSentry() {
        // Tracker removed
    }
}
