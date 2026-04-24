/*
 * Copyright (c) 2026 Proton AG
 *
 * This file is part of ProtonVPN.
 *
 * ProtonVPN is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package me.proton.core.observability.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObservabilityManager @Inject constructor() {
    fun enqueue(event: Any) {}
    fun enqueue(data: Any, instant: Any) {}
}
