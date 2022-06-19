package com.ruskaof.feature_product_impl.domain.repository

import com.ruskaof.core_network_api.models.ProductInListDTO


interface ProductsListRepository {
    fun getProductsList(): List<ProductInListDTO>
    fun increaseViewCounter(guid: String)
}