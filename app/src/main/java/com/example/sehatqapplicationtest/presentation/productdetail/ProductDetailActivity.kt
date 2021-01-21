package com.example.sehatqapplicationtest.presentation.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sehatqapplicationtest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
    }
}