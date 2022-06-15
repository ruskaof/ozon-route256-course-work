package com.ruskaof.homework2.presentation.mappers

import com.ruskaof.homework2.data.dto.ProductInListDTO
import com.ruskaof.homework2.presentation.viewObject.ProductInListVO

fun ProductInListDTO.toVO() = ProductInListVO(
    guid, image, name, price, rating, isFavorite, isInCart, viewCounter
)