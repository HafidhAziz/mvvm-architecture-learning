package com.example.sehatqapplicationtest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.data.ProductPromoEntity
import com.example.sehatqapplicationtest.databinding.ItemHomeProductListBinding

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

class HomeProductListAdapter(
    private val products: ArrayList<ProductPromoEntity>
) : RecyclerView.Adapter<HomeProductListAdapter.DataViewHolder>() {

    var listener: ClickItemListener? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemHomeProductListBinding = DataBindingUtil.bind(itemView)!!
        var listener: ClickItemListener? = null

        fun bind(products: ProductPromoEntity) {
            binding.name.text = products.title
            Glide.with(binding.image.context)
                .load(products.imageUrl)
                .into(binding.image)
            if (products.loved == 1) {
                binding.favorite.setBackgroundResource(R.drawable.ic_fav_selected)
            } else {
                binding.favorite.setBackgroundResource(R.drawable.ic_fav)
            }
            itemView.setOnClickListener {
                listener?.onClickItemListener(products)
            }
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

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.listener = listener
        holder.bind(products[position])
    }

    fun addData(list: List<ProductPromoEntity>) {
        products.addAll(list)
    }

    fun setClickItemListener(listener: ClickItemListener) {
        this.listener = listener
    }

    interface ClickItemListener {
        fun onClickItemListener(productPromoEntity: ProductPromoEntity)
    }
}