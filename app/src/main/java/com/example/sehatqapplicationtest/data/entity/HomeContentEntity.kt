package com.example.sehatqapplicationtest.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

data class HomeContentEntity(
    @SerializedName("category") val category: List<CategoryEntity>?,
    @SerializedName("productPromo") val productPromo: List<ProductPromoEntity>?
) {
    data class CategoryEntity(
        @SerializedName("id") val id: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("imageUrl") val imageUrl: String?
    )

    data class ProductPromoEntity(
        @SerializedName("id") val id: String?,
        @SerializedName("imageUrl") val imageUrl: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("price") val price: String?,
        @SerializedName("loved") val loved: String?

    )
}