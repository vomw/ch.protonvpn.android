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

package com.protonvpn.android.api

/**
 * Central configuration for Web Proxy to access VPN API.
 * This can be used to bypass ISP blocks on vpn-api.proton.me.
 */
object WebProxyConfig {
    /**
     * Set to true to enable web proxy.
     */
    const val isProxyEnabled = true

    /**
     * The URL of the web proxy.
     * The target host will be appended as a path segment.
     * Example: if proxyUrl is "https://cors.eu.org" and target host is "vpn-api.proton.me",
     * the resulting base URL will be "https://cors.eu.org/vpn-api.proton.me/".
     */
    const val proxyUrl = "https://cors.eu.org"
}
