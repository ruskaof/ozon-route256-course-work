package com.ruskaof.homework2.domain.repositories

import com.ruskaof.homework2.data.dto.ProductDTO
import com.ruskaof.homework2.data.dto.ProductInListDTO

interface ProductsRepository {
    fun getProducts(): List<ProductInListDTO>
    fun getProductById(guid: String): ProductDTO
    fun increaseViewCounter(guid: String)
}