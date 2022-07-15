package com.ruskaof.feature_product_impl.domain.repository

import com.ruskaof.core_network_api.models.ProductInListDTO
import kotlinx.coroutines.flow.Flow


interface ProductsListRepository {
    fun getProductsList(): Flow<List<ProductInListDTO>?>
    fun increaseViewCounter(guid: String)
    fun updateData()
}