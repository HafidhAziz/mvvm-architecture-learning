package com.example.sehatqapplicationtest.di

import android.content.Context
import androidx.room.Room
import com.example.sehatqapplicationtest.db.AppDatabase
import com.example.sehatqapplicationtest.db.PurchaseHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun providePurchaseHistoryDao(database: AppDatabase): PurchaseHistoryDao {
        return database.purchaseHistoryDao()
    }
}