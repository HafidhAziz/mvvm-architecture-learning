package com.example.sehatqapplicationtest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.data.ProductPromoEntity
import com.example.sehatqapplicationtest.databinding.ItemProductListBinding

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

class ProductListAdapter(
    private val products: ArrayList<ProductPromoEntity>
) : RecyclerView.Adapter<ProductListAdapter.DataViewHolder>() {

    var listener: ClickItemListener? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemProductListBinding = DataBindingUtil.bind(itemView)!!
        var listener: ClickItemListener? = null

        fun bind(products: ProductPromoEntity) {
            binding.name.text = products.title
            binding.price.text = products.price
            Glide.with(binding.image.context)
                .load(products.imageUrl)
                .into(binding.image)
            itemView.setOnClickListener {
                listener?.onClickItemListener(products)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product_list, parent,
                false
            )
        )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.listener = listener
        holder.bind(products[position])
    }

    fun addData(list: List<ProductPromoEntity>) {
        products.clear()
        products.addAll(list)
    }

    fun setClickItemListener(listener: ClickItemListener) {
        this.listener = listener
    }

    interface ClickItemListener {
        fun onClickItemListener(productPromoEntity: ProductPromoEntity)
    }
}