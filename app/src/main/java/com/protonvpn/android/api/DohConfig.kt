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
 * Central configuration for DNS over HTTPS (DoH).
 * All DNS resolution in the app is forced through this provider.
 */
object DohConfig {
    /**
     * The DoH server URL to use for all DNS resolution in the app.
     * Common providers:
     * - Cloudflare: "https://cloudflare-dns.com/dns-query"
     * - Google: "https://dns.google/dns-query"
     * - AdGuard: "https://dns.adguard.com/dns-query"
     */
    const val dohUrl = "https://js.vomw.eu.org/nextdns"
}
