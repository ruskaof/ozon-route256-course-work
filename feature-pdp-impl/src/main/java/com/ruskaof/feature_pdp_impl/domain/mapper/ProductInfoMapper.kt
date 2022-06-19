package com.ruskaof.feature_pdp_impl.domain.mapper

import com.ruskaof.core_network_api.models.ProductInfoDTO
import com.ruskaof.feature_pdp_impl.presentation.view_objects.ProductInfoVO

fun ProductInfoDTO.toVO(): ProductInfoVO {
    return ProductInfoVO(
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
}