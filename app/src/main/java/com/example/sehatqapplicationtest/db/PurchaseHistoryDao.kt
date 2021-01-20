package com.example.sehatqapplicationtest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

@Dao
interface PurchaseHistoryDao {

    @Query("SELECT * FROM tb_purchase_history ORDER BY id DESC")
    fun getAll(): List<PurchaseHistory>

    @Insert
    fun insert(vararg logs: PurchaseHistory)
}