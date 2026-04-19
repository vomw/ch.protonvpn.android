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

import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor that redirects requests to a web proxy if the [WebProxyConfig.HEADER_USE_PROXY]
 * header is present.
 */
class WebProxyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        
        // Only proxy if enabled and header is present
        if (!WebProxyConfig.isProxyEnabled || request.header(WebProxyConfig.HEADER_USE_PROXY) == null) {
            return chain.proceed(request)
        }

        val originalUrl = request.url
        val proxiedUrl = WebProxyConfig.proxyUrl.toHttpUrl().newBuilder()
            .addPathSegment(originalUrl.host)
            .apply {
                originalUrl.pathSegments.forEach { addPathSegment(it) }
            }
            .query(originalUrl.query)
            .build()

        val newRequest = request.newBuilder()
            .url(proxiedUrl)
            .removeHeader(WebProxyConfig.HEADER_USE_PROXY)
            .build()

        return chain.proceed(newRequest)
    }
}
