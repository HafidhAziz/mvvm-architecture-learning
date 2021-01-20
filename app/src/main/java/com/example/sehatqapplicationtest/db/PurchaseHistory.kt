package com.example.sehatqapplicationtest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */
@Entity(tableName = "tb_purchase_history")
data class PurchaseHistory(val productId: String, val name: String, val price: String, val image: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}