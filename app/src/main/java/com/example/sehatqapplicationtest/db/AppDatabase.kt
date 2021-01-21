package com.example.sehatqapplicationtest.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

@Database(entities = [PurchaseHistory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun purchaseHistoryDao() : PurchaseHistoryDao

    companion object{
        const val DATABASE_NAME: String = "app.db"
    }
}