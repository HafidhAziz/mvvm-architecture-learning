package com.example.sehatqapplicationtest.presentation.purchasehistory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ItemProductListBinding
import com.example.sehatqapplicationtest.db.PurchaseHistory

/**
 * Created by M Hafidh Abdul Aziz on 22/01/21.
 */

class PurchaseHistoryAdapter(
    private val purchaseHistory: ArrayList<PurchaseHistory>
) : RecyclerView.Adapter<PurchaseHistoryAdapter.DataViewHolder>() {

    var listener: ClickItemListener? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemProductListBinding = DataBindingUtil.bind(itemView)!!
        var listener: ClickItemListener? = null

        fun bind(purchaseHistory: PurchaseHistory) {
            binding.name.text = purchaseHistory.title
            binding.price.text = purchaseHistory.price
            Glide.with(binding.image.context)
                .load(purchaseHistory.imageUrl)
                .into(binding.image)
            itemView.setOnClickListener {
                listener?.onClickItemListener(purchaseHistory)
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

    override fun getItemCount(): Int = purchaseHistory.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.listener = listener
        holder.bind(purchaseHistory[position])
    }

    fun addData(list: List<PurchaseHistory>) {
        purchaseHistory.clear()
        purchaseHistory.addAll(list)
    }

    fun setClickItemListener(listener: ClickItemListener) {
        this.listener = listener
    }

    interface ClickItemListener {
        fun onClickItemListener(purchaseHistory: PurchaseHistory)
    }
}