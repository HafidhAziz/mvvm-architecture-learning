package com.example.sehatqapplicationtest.db

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

class PurchaseHistoryLocalDataSource @Inject constructor(private val purchaseHistoryDao: PurchaseHistoryDao) :
    PurchaseHistoryDataSource {

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun addPurchase(productId: String, name: String, price: String, image: String) {
        executorService.execute {
            purchaseHistoryDao.insert(
                PurchaseHistory(
                    productId, name, price, image
                )
            )
        }
    }

    override fun getAllPurchaseHistory(callback: (List<PurchaseHistory>) -> Unit) {
        executorService.execute {
            val logs = purchaseHistoryDao.getAll()
            mainThreadHandler.post { callback(logs) }
        }
    }
}