package com.ruskaof.feature_product_impl.presentation.view_objects

data class ProductInListVO(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val viewCounter: String,
    val isExpensive: Boolean,
    val priceBeforeDiscount: String,
    val discount: String?,
    val thereWasDiscount: Boolean,
    val shippingDate: String,
    val shippingCompanyName: String,
    val sellerName: String,
    val reviewCount: String
)