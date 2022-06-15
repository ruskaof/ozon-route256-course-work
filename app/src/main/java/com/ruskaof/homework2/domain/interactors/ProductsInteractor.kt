package com.ruskaof.homework2.domain.interactors

import com.ruskaof.homework2.presentation.viewObject.ProductInListVO
import com.ruskaof.homework2.presentation.viewObject.ProductVO

interface ProductsInteractor {
    fun getProducts(): List<ProductInListVO>
    fun getProductById(guid: String): ProductVO
    fun increaseViewCounter(guid: String)
}