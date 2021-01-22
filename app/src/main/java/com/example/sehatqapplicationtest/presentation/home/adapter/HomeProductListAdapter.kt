package com.example.sehatqapplicationtest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.data.ProductPromoEntity
import com.example.sehatqapplicationtest.databinding.ItemCategoryBinding
import com.example.sehatqapplicationtest.databinding.ItemHomeProductListBinding
import com.example.sehatqapplicationtest.databinding.ItemProductListBinding

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

class HomeProductListAdapter(
    private val products: ArrayList<ProductPromoEntity>
) : RecyclerView.Adapter<HomeProductListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemHomeProductListBinding = DataBindingUtil.bind(itemView)!!

        fun bind(products: ProductPromoEntity) {
            binding.name.text = products.title
            Glide.with(binding.image.context)
                .load(products.imageUrl)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_home_product_list, parent,
                false
            )
        )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(products[position])

    fun addData(list: List<ProductPromoEntity>) {
        products.addAll(list)
    }
}