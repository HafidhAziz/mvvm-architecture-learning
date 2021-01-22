package com.example.sehatqapplicationtest.data

import com.squareup.moshi.Json

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */
data class HomeContentEntity(
    @Json(name = "data")
    val data: DataEntity? = null,
)

data class DataEntity(
    @Json(name = "category")
    val category: List<CategoryEntity>? = null,
    @Json(name = "productPromo")
    val productPromo: List<ProductPromoEntity>? = null
)

data class CategoryEntity(
    @Json(name = "id")
    val id: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "imageUrl")
    val imageUrl: String? = ""
)

data class ProductPromoEntity(
    @Json(name = "id")
    val id: String? = "",
    @Json(name = "imageUrl")
    val imageUrl: String? = "",
    @Json(name = "title")
    val title: String? = "",
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "price")
    val price: String? = "",
    @Json(name = "loved")
    val loved: String? = ""

)
