package com.example.sehatqapplicationtest.presentation.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ActivityProductDetailBinding
import com.example.sehatqapplicationtest.db.PurchaseHistoryDataSource
import com.example.sehatqapplicationtest.presentation.purchasehistory.PurchaseHistoryActivity
import com.example.sehatqapplicationtest.util.AppConstants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity(), ProductDetailView {

    @Inject
    lateinit var purchaseHistory: PurchaseHistoryDataSource
    lateinit var binding: ActivityProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()

    companion object {

        fun startThisActivity(
            context: Context,
            productPromoStr: String,
            isFromPurchaseHistory: Boolean
        ) {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(AppConstants.EXTRA_PRODUCT_PROMO_STR, productPromoStr)
            intent.putExtra(AppConstants.EXTRA_FROM_PURCHASE_HISTORY, isFromPurchaseHistory)
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
            productStr = intent?.getStringExtra(AppConstants.EXTRA_PRODUCT_PROMO_STR).orEmpty(),
            isFromPurchase = intent?.getBooleanExtra(
                AppConstants.EXTRA_FROM_PURCHASE_HISTORY,
                false
            ) ?: false
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

            if (viewModel.getProduct()?.loved == 1) {
                fav.setImageResource(R.drawable.ic_fav_selected)
            } else {
                fav.setImageResource(R.drawable.ic_fav)
            }

            if (viewModel.isFromPurchaseHistory()) {
                clFooter.visibility = View.GONE
            }

            fav.setOnClickListener {
                if (viewModel.isLoved()) {
                    fav.setImageResource(R.drawable.ic_fav)
                } else {
                    fav.setImageResource(R.drawable.ic_fav_selected)
                }
                viewModel.loved = !viewModel.isLoved()
            }

            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnShare.setOnClickListener {
                onClickShare()
            }

            btnBuy.setOnClickListener {
                onClickBuy()
            }
        }
    }

    override fun onClickShare() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            val shareMessage = getString(R.string.share_content)
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, getString(R.string.choose_app)))
        } catch (e: Exception) {
            Log.i("error", e.toString())
        }
    }

    override fun onClickBuy() {
        purchaseHistory.addPurchase(
            viewModel.getProduct()?.title.orEmpty(),
            viewModel.getProduct()?.description.orEmpty(),
            viewModel.getProduct()?.price.orEmpty(),
            viewModel.getProduct()?.imageUrl.orEmpty()
        )
        Toast.makeText(this, "Pembelian berhasil", Toast.LENGTH_LONG).show()
        PurchaseHistoryActivity.startThisActivity(this)
    }
}