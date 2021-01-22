package com.example.sehatqapplicationtest.presentation.purchasehistory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ActivityPurchaseHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseHistoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityPurchaseHistoryBinding

    companion object {

        fun startThisActivity(context: Context) {
            val intent = Intent(context, PurchaseHistoryActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_history)
    }
}