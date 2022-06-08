package com.ruskaof.homework2.data.repositoriesImpl

import com.ruskaof.homework2.data.dto.ProductDTO
import com.ruskaof.homework2.data.dto.ProductInListDTO
import com.ruskaof.homework2.domain.repositories.ProductsRepository

class MockProductsRepositoryImpl : ProductsRepository {
    override fun getProducts(): List<ProductInListDTO> {
        return mockProductsInList
    }

    override fun getProductById(guid: String): ProductDTO {
        return mockProducts.find { product -> product.guid == guid }!!
    }

    override fun increaseViewCounter(guid: String) {
        mockProductsInList.find { it.guid == guid }!!.viewCounter++
    }
}