package com.example.sehatqapplicationtest.db

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

interface PurchaseHistoryDataSource {
    fun addPurchase(productId: String, name: String, price: String, image: String)
    fun getAllPurchaseHistory(callback: (List<PurchaseHistory>) -> Unit)
}