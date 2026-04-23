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

package com.protonvpn.android.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import me.proton.core.configuration.EnvironmentConfiguration
import me.proton.core.featureflag.domain.FeatureFlagOverrider
import me.proton.core.payment.domain.repository.GoogleBillingRepository
import me.proton.core.payment.domain.usecase.FindGooglePurchaseForPaymentOrderId
import me.proton.core.payment.domain.usecase.FindUnacknowledgedGooglePurchase
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [
        me.proton.core.configuration.dagger.StaticEnvironmentConfigModule::class
    ]
)
object HiltTestStubsModule {
    @Provides
    @Singleton
    fun provideGoogleBillingRepository(): GoogleBillingRepository<Activity> = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideFindGooglePurchaseForPaymentOrderId(): FindGooglePurchaseForPaymentOrderId = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideFindUnacknowledgedGooglePurchase(): FindUnacknowledgedGooglePurchase = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideEnvironmentConfiguration(): EnvironmentConfiguration = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideFeatureFlagOverrider(): FeatureFlagOverrider = mockk(relaxed = true)
}
