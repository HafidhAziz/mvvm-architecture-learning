package com.example.sehatqapplicationtest.presentation.home

import com.example.sehatqapplicationtest.data.CategoryEntity
import com.example.sehatqapplicationtest.data.ProductPromoEntity

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

interface HomeView {
    fun setupUI()
    fun setupObserver()
    fun renderCategoryList(users: List<CategoryEntity>)
    fun renderProductList(users: List<ProductPromoEntity>)
    fun setupToolbar()
}