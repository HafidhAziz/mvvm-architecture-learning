package com.example.sehatqapplicationtest.data

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("response")
	val response: List<ResponseItem?>? = null
)

data class Data(

	@field:SerializedName("productPromo")
	val productPromo: List<ProductPromoItem?>? = null,

	@field:SerializedName("category")
	val category: List<CategoryItem?>? = null
)

data class ProductPromoItem(

	@field:SerializedName("loved")
	val loved: Int? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class CategoryItem(

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class ResponseItem(

	@field:SerializedName("data")
	val data: Data? = null
)
