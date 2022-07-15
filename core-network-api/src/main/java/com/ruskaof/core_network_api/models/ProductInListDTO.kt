package com.ruskaof.core_network_api.models

import com.google.gson.annotations.SerializedName


data class ProductInListDTO(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: Int,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    @SerializedName("price_before_discount")
    val priceBeforeDiscount: Int,
    val viewCounter: Long,
    val shippingDate: String,
    val shippingCompanyName: String,
    val sellerName: String,
    val reviewCount: Long
)