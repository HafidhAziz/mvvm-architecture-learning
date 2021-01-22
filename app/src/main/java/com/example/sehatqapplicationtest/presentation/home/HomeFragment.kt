package com.example.sehatqapplicationtest.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.sehatqapplicationtest.util.Status
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeView {

    lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var promoProductAdapter: HomeProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setupUI()
        setupObserver()

        return binding.root
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
        binding.productListRecycler.adapter = promoProductAdapter
    }

    override fun setupObserver() {
        homeViewModel.homeData.observe(requireActivity(), {
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


}