/*
 * Copyright (c) 2023 Proton AG
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

package com.protonvpn.android.auth

import me.proton.core.account.domain.repository.AccountRepository
import me.proton.core.accountmanager.data.SessionListenerImpl
import me.proton.core.accountmanager.domain.SessionManager
import me.proton.core.network.domain.session.Session
import javax.inject.Inject

class VpnSessionListener @Inject constructor(
    private val accountRepository: dagger.Lazy<AccountRepository>,
    sessionManager: dagger.Lazy<SessionManager>
) : SessionListenerImpl(sessionManager) {

    override suspend fun onSessionForceLogout(session: Session, httpCode: Int) {
        super.onSessionForceLogout(session, httpCode)
    }
}
