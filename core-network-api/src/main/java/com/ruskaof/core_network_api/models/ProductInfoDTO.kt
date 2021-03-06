package com.ruskaof.core_network_api.models

data class ProductInfoDTO(
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
