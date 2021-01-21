package com.example.sehatqapplicationtest.presentation.productsearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ActivityProductSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductSearchActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_search)

    }
}