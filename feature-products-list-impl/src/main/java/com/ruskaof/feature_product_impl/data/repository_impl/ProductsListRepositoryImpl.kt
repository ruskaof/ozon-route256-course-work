package com.ruskaof.feature_product_impl.data.repository_impl

import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productApi: ProductApi) :
    ProductsListRepository {
    override fun getProductsList(): List<ProductInListDTO> {
        return productApi.getProductsList()
    }

    override fun increaseViewCounter(guid: String) {
        productApi.increaseViewCounter(guid)
    }
}