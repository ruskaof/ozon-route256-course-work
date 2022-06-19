package com.ruskaof.feature_product_impl.domain.mapper

import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO

fun ProductInListDTO.toVO(): ProductInListVO {
    return ProductInListVO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        viewCounter = viewCounter
    )
}