package com.ruskaof.homework2.presentation.mappers

import com.ruskaof.homework2.data.dto.ProductDTO
import com.ruskaof.homework2.presentation.viewObject.ProductVO

fun ProductDTO.toVO() = ProductVO(
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