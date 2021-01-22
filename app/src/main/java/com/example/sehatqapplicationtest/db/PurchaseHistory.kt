package com.example.sehatqapplicationtest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */
@Entity(tableName = "tb_purchase_history")
data class PurchaseHistory(val title: String, val description: String, val price: String, val imageUrl: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}