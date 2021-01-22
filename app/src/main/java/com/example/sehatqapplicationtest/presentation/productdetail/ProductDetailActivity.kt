package com.example.sehatqapplicationtest.presentation.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ActivityProductDetailBinding
import com.example.sehatqapplicationtest.util.AppConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity(), ProductDetailView {

    lateinit var binding: ActivityProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()

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

        getArguments()
        setupViews()
    }

    override fun getArguments() {
        viewModel.initExtrasData(
            productStr = intent?.getStringExtra(AppConstants.EXTRA_PRODUCT_PROMO_STR).orEmpty()
        )
    }

    override fun setupViews() {
        binding.apply {
            productDetailName.text = viewModel.getProduct()?.title
            productDetailDesc.text = viewModel.getProduct()?.description
            productDetailPrice.text = viewModel.getProduct()?.price
            Glide.with(productDetailImage.context)
                .load(viewModel.getProduct()?.imageUrl)
                .into(productDetailImage)

            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnShare.setOnClickListener {

            }

            btnBuy.setOnClickListener {

            }
        }
    }
}