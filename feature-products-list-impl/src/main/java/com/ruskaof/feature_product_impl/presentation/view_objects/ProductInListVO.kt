package com.ruskaof.feature_product_impl.presentation.view_objects

data class ProductInListVO(
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    var viewCounter: Int
)