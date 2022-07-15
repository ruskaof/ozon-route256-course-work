package com.ruskaof.data_updater_api.model

import com.ruskaof.core_network_api.models.ProductInListDTO
import kotlinx.serialization.Serializable

@Serializable
data class ProductInListSaveObject(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: Int,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val priceBeforeDiscount: Int,
    val viewCounter: Long,
    val shippingDate: String,
    val shippingCompanyName: String,
    val sellerName: String,
    val reviewCount: Long
)

fun ProductInListSaveObject.toDTO(): ProductInListDTO = ProductInListDTO(
    guid,
    image,
    name,
    price,
    rating,
    isFavorite,
    isInCart,
    priceBeforeDiscount,
    viewCounter,
    shippingDate,
    shippingCompanyName,
    sellerName,
    reviewCount
)

fun ProductInListDTO.toSaveObject(): ProductInListSaveObject = ProductInListSaveObject(
    guid,
    image,
    name,
    price,
    rating,
    isFavorite,
    isInCart,
    priceBeforeDiscount,
    viewCounter,
    shippingDate,
    shippingCompanyName,
    sellerName,
    reviewCount
)
