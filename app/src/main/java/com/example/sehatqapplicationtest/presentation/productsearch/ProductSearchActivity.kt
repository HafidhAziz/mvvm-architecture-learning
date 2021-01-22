package com.example.sehatqapplicationtest.presentation.productsearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.data.DataEntity
import com.example.sehatqapplicationtest.data.ProductPromoEntity
import com.example.sehatqapplicationtest.databinding.ActivityProductSearchBinding
import com.example.sehatqapplicationtest.presentation.home.adapter.ProductListAdapter
import com.example.sehatqapplicationtest.presentation.productdetail.ProductDetailActivity
import com.example.sehatqapplicationtest.util.CommonUtils
import com.example.sehatqapplicationtest.util.ViewUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductSearchActivity : AppCompatActivity(), ProductSearchView,
    ProductListAdapter.ClickItemListener {

    lateinit var binding: ActivityProductSearchBinding
    private var productList: ArrayList<ProductPromoEntity> = ArrayList()
    private var productListTemp = arrayListOf<ProductPromoEntity>()
    private lateinit var productListAdapter: ProductListAdapter

    companion object {

        fun startThisActivity(context: Context) {
            val intent = Intent(context, ProductSearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_search)

        setupToolbar()
        setupUI()
        setupMockData()
    }

    override fun setupToolbar() {
        binding.toolbarProductSearch.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
            btnClear.setOnClickListener {
                searchInputQuery.text = null
                btnClear.visibility = View.GONE
            }
            searchInputQuery.setOnEditorActionListener(bOnEditorActionListener)
            searchInputQuery.addTextChangedListener(bTextWatcherSearch)
        }
    }

    override fun setupUI() {
        val productSearchLayoutManager = LinearLayoutManager(this)
        productSearchLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.productSearchRecycler.layoutManager = productSearchLayoutManager
        productListAdapter = ProductListAdapter(arrayListOf())
        productListAdapter.setClickItemListener(this)
        binding.productSearchRecycler.adapter = productListAdapter
    }

    override fun setupMockData() {
        val mockFile = "ProductSearchMockData.json"

        val homeData = CommonUtils.convertStreamToJsonClass(this, mockFile, DataEntity::class.java)

        homeData?.let {
            productList.clear()
            productList = it.productPromo as ArrayList<ProductPromoEntity>
            productListTemp.addAll(productList)
            productListAdapter.addData(productListTemp)
            productListAdapter.notifyDataSetChanged()
        }
    }

    override var bOnEditorActionListener: TextView.OnEditorActionListener
        get() = TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                ViewUtils.hideKeyboard(this, binding.toolbarProductSearch.searchInputQuery)
                return@OnEditorActionListener true
            }
            false
        }
        set(_) {}

    override var bTextWatcherSearch: TextWatcher
        get() = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.isBlank()) {
                        productListTemp.clear()
                        productListTemp.addAll(productList)
                        productListAdapter.addData(productListTemp)
                        productListAdapter.notifyDataSetChanged()
                        binding.apply {
                            toolbarProductSearch.btnClear.visibility = View.GONE
                            emptySearchLayout.visibility = View.GONE
                        }
                    } else {
                        binding.apply {
                            toolbarProductSearch.btnClear.visibility = View.VISIBLE
                            emptySearchLayout.visibility = View.GONE
                        }
                        val newList = getFilterList(s.toString())
                        productListTemp.clear()
                        if (newList.isNotEmpty()) {
                            productListTemp.addAll(ArrayList(newList))
                            productListAdapter.addData(productListTemp)
                        } else {
                            productListTemp.addAll(arrayListOf())
                            productListAdapter.addData(productListTemp)
                            binding.apply {
                                emptySearchLayout.visibility = View.VISIBLE
                            }
                        }
                        productListAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
        set(_) {}

    private fun getFilterList(s: String): List<ProductPromoEntity> {
        val newValues = arrayListOf<ProductPromoEntity>()
        for (value in productList) {
            if (value.title.isNullOrBlank()) {
                if (value.title?.contains(s, true)!!) {
                    newValues.add(value)
                }
            } else {
                if (value.title.contains(s, true)) {
                    newValues.add(value)
                }
            }
        }
        return newValues
    }

    override fun onClickItemListener(productPromoEntity: ProductPromoEntity) {
        ProductDetailActivity.startThisActivity(
            this,
            CommonUtils.convertClassToJson(productPromoEntity).orEmpty(),
            false
        )
    }
}