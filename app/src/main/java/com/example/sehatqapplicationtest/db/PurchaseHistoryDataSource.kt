package com.example.sehatqapplicationtest.db

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

interface PurchaseHistoryDataSource {
    fun addPurchase(title: String, description: String, price: String, imageUrl: String)
    fun getAllPurchaseHistory(callback: (List<PurchaseHistory>) -> Unit)
}