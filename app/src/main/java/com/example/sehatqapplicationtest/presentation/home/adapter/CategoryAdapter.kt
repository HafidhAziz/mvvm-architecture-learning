package com.example.sehatqapplicationtest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.data.CategoryEntity
import com.example.sehatqapplicationtest.databinding.ItemCategoryBinding

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

class CategoryAdapter(
    private val categories: ArrayList<CategoryEntity>
) : RecyclerView.Adapter<CategoryAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemCategoryBinding = DataBindingUtil.bind(itemView)!!

        fun bind(category: CategoryEntity) {
            binding.categoryName.text = category.name
            Glide.with(binding.categoryImage.context)
                .load(category.imageUrl)
                .into(binding.categoryImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category, parent,
                false
            )
        )

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(categories[position])

    fun addData(list: List<CategoryEntity>) {
        categories.addAll(list)
    }
}