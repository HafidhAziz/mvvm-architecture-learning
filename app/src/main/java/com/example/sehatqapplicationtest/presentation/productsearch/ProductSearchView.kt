package com.example.sehatqapplicationtest.presentation.productsearch

import android.text.TextWatcher
import android.widget.TextView

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

interface ProductSearchView {
    fun setupToolbar()
    fun setupUI()
    fun setupMockData()
    var bOnEditorActionListener: TextView.OnEditorActionListener
    var bTextWatcherSearch: TextWatcher
}