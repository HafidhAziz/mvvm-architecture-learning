package com.example.sehatqapplicationtest.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.data.CategoryEntity
import com.example.sehatqapplicationtest.data.ProductPromoEntity
import com.example.sehatqapplicationtest.databinding.FragmentHomeBinding
import com.example.sehatqapplicationtest.presentation.home.adapter.CategoryAdapter
import com.example.sehatqapplicationtest.presentation.home.adapter.HomeProductListAdapter
import com.example.sehatqapplicationtest.presentation.productdetail.ProductDetailActivity
import com.example.sehatqapplicationtest.presentation.productsearch.ProductSearchActivity
import com.example.sehatqapplicationtest.util.CommonUtils
import com.example.sehatqapplicationtest.util.Status
import com.example.sehatqapplicationtest.util.ViewUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeView, HomeProductListAdapter.ClickItemListener {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var promoProductAdapter: HomeProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setupToolbar()
        setupUI()
        setupObserver()

        return binding.root
    }

    override fun setupToolbar() {
        binding.toolbarHome.apply {
            btnBack.visibility = View.GONE
            btnFav.visibility = View.VISIBLE
            searchInputQuery.setOnFocusChangeListener { view, focused ->
                if (focused) {
                    ViewUtils.hideKeyboard(requireContext(), searchInputQuery)
                    view.clearFocus()
                    ProductSearchActivity.startThisActivity(requireContext())
                }
            }
        }
    }

    override fun setupUI() {
        val categoryLayoutManager = LinearLayoutManager(activity)
        categoryLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.categoryRecycler.layoutManager = categoryLayoutManager
        categoryAdapter = CategoryAdapter(arrayListOf())
        binding.categoryRecycler.adapter = categoryAdapter


        val promoProductLayoutManager = LinearLayoutManager(activity)
        promoProductLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.productListRecycler.layoutManager = promoProductLayoutManager
        promoProductAdapter = HomeProductListAdapter(arrayListOf())
        promoProductAdapter.setClickItemListener(this)
        binding.productListRecycler.adapter = promoProductAdapter
    }

    override fun setupObserver() {
        viewModel.homeData.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.get(0)?.data?.category?.let { categories ->
                        renderCategoryList(categories)
                        binding.categoryRecycler.visibility = View.VISIBLE
                    }
                    it.data?.get(0)?.data?.productPromo?.let { products ->
                        renderProductList(products)
                        binding.productListRecycler.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        categoryRecycler.visibility = View.GONE
                        productListRecycler.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun renderCategoryList(users: List<CategoryEntity>) {
        categoryAdapter.addData(users)
        categoryAdapter.notifyDataSetChanged()
    }

    override fun renderProductList(users: List<ProductPromoEntity>) {
        promoProductAdapter.addData(users)
        promoProductAdapter.notifyDataSetChanged()
    }

    override fun onClickItemListener(productPromoEntity: ProductPromoEntity) {
        ProductDetailActivity.startThisActivity(
            requireContext(),
            CommonUtils.convertClassToJson(productPromoEntity).orEmpty()
        )
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}