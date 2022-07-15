package com.ruskaof.data_updater_api.model

import com.ruskaof.core_network_api.models.ProductInfoDTO
import kotlinx.serialization.Serializable

@Serializable
data class ProductInfoSaveObject(
    val guid: String,
    val name: String,
    val price: Int,
    val description: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val images: List<String>,
    val weight: Double?,
    val count: Int?,
    val availableCount: Int?,
    val additionalParams: Map<String, String>
)


fun ProductInfoSaveObject.toDTO(): ProductInfoDTO = ProductInfoDTO(
    guid,
    name,
    price,
    description,
    rating,
    isFavorite,
    isInCart,
    images,
    weight,
    count,
    availableCount,
    additionalParams
)

fun ProductInfoDTO.toSaveObject(): ProductInfoSaveObject = ProductInfoSaveObject(
    guid,
    name,
    price,
    description,
    rating,
    isFavorite,
    isInCart,
    images,
    weight,
    count,
    availableCount,
    additionalParams
)