package com.example.sehatqapplicationtest.data.viewparam

import com.example.sehatqapplicationtest.data.entity.HomeContentEntity

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

data class HomeEntityViewParam(
    val category: List<CategoryViewParam>,
    val promoCode: List<PromoCodeViewParam>
) {
    constructor(homeContentEntity: HomeContentEntity?) : this(
        category = homeContentEntity?.category?.map {
            CategoryViewParam(it)
        }.orEmpty(),
        promoCode = homeContentEntity?.productPromo?.map {
            PromoCodeViewParam(it)
        }.orEmpty()
    )

    data class CategoryViewParam(
        val id: String,
        val name: String,
        val imageUrl: String
    ) {
        constructor(categoryEntity: HomeContentEntity.CategoryEntity?) : this(
            id = categoryEntity?.id.orEmpty(),
            name = categoryEntity?.name.orEmpty(),
            imageUrl = categoryEntity?.imageUrl.orEmpty()
        )
    }

    data class PromoCodeViewParam(
        val id: String,
        val imageUrl: String,
        val title: String,
        val description: String,
        val price: String,
        val loved: String,
    ) {
        constructor(promoEntity: HomeContentEntity.ProductPromoEntity?) : this(
            id = promoEntity?.id.orEmpty(),
            imageUrl = promoEntity?.imageUrl.orEmpty(),
            title = promoEntity?.title.orEmpty(),
            description = promoEntity?.description.orEmpty(),
            price = promoEntity?.price.orEmpty(),
            loved = promoEntity?.loved.orEmpty()
        )
    }
}