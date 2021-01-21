package com.example.sehatqapplicationtest.presentation.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ActivityProductDetailBinding
import com.example.sehatqapplicationtest.presentation.main.MainActivity
import com.example.sehatqapplicationtest.util.AppConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity(), ProductDetailView {

    lateinit var binding: ActivityProductDetailBinding

    companion object {

        fun startThisActivity(context: Context, productPromoStr: String) {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(AppConstants.EXTRA_PRODUCT_PROMO_STR, productPromoStr)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        setupViews()
    }

    override fun setupViews() {
        binding.productDetailName.text = ""
        binding.productDetailDesc.text = ""
        binding.productDetailPrice.text = ""

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnShare.setOnClickListener {

        }

        binding.btnBuy.setOnClickListener {

        }
    }
}