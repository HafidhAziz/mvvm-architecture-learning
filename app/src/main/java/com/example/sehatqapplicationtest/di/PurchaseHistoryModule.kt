package com.example.sehatqapplicationtest.di

import com.example.sehatqapplicationtest.db.PurchaseHistoryDataSource
import com.example.sehatqapplicationtest.db.PurchaseHistoryLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

@InstallIn(ApplicationComponent::class)
@Module
abstract class PurchaseHistoryModule {

    @Singleton
    @Binds
    abstract fun bindPurchaseHistory(impl: PurchaseHistoryLocalDataSource): PurchaseHistoryDataSource
}