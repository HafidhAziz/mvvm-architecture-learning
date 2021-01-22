package com.example.sehatqapplicationtest.presentation.purchasehistory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ActivityPurchaseHistoryBinding
import com.example.sehatqapplicationtest.db.PurchaseHistory
import com.example.sehatqapplicationtest.db.PurchaseHistoryDataSource
import com.example.sehatqapplicationtest.presentation.productdetail.ProductDetailActivity
import com.example.sehatqapplicationtest.presentation.purchasehistory.adapter.PurchaseHistoryAdapter
import com.example.sehatqapplicationtest.util.CommonUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PurchaseHistoryActivity : AppCompatActivity(), PurchaseHistoryView,
    PurchaseHistoryAdapter.ClickItemListener {

    @Inject
    lateinit var purchaseHistory: PurchaseHistoryDataSource
    lateinit var binding: ActivityPurchaseHistoryBinding
    private lateinit var purchaseHistoryAdapter: PurchaseHistoryAdapter

    companion object {

        fun startThisActivity(context: Context) {
            val intent = Intent(context, PurchaseHistoryActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_purchase_history)

        setupToolbar()
        setupUI()
        setupData()
    }

    override fun setupToolbar() {
        binding.toolbarPurchaseHistory.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
            searchLayout.visibility = View.GONE
            tvTitle.apply {
                text = getString(R.string.purchase_history)
                visibility = View.VISIBLE
            }
        }
    }

    override fun setupUI() {
        val purchaseHistoryLayoutManager = LinearLayoutManager(this)
        purchaseHistoryLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.purchaseHistoryRecycler.layoutManager = purchaseHistoryLayoutManager
        purchaseHistoryAdapter = PurchaseHistoryAdapter(arrayListOf())
        purchaseHistoryAdapter.setClickItemListener(this)
        binding.purchaseHistoryRecycler.adapter = purchaseHistoryAdapter
    }

    override fun setupData() {
        purchaseHistory.getAllPurchaseHistory {
            purchaseHistoryAdapter.addData(it)
            purchaseHistoryAdapter.notifyDataSetChanged()
        }
    }

    override fun onClickItemListener(purchaseHistory: PurchaseHistory) {
        ProductDetailActivity.startThisActivity(
            this,
            CommonUtils.convertClassToJson(purchaseHistory).orEmpty(),
            true
        )
    }
}