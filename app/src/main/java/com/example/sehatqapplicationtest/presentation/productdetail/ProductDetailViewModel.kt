package com.example.sehatqapplicationtest.presentation.productdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.sehatqapplicationtest.data.ProductPromoEntity
import com.example.sehatqapplicationtest.util.CommonUtils

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

class ProductDetailViewModel @ViewModelInject constructor() : ViewModel() {

    private var product: ProductPromoEntity? = null
    fun getProduct(): ProductPromoEntity? = product

    private var fromPurchase: Boolean = false
    fun isFromPurchaseHistory(): Boolean = fromPurchase

    fun initExtrasData(productStr: String, isFromPurchase: Boolean) {
        this.product = CommonUtils.convertJsonToClass(productStr, ProductPromoEntity::class.java)
        this.fromPurchase = isFromPurchase
    }
}