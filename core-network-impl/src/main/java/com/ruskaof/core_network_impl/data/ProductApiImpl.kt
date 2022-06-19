package com.ruskaof.core_network_impl.data

import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import javax.inject.Inject

class ProductApiImpl @Inject constructor(): ProductApi {
    override fun getProductsList(): List<ProductInListDTO> {
        return mockProductsInList
    }

    override fun getProductInfo(guid: String): ProductInfoDTO {
        return mockProducts.first { it.guid == guid }
    }

    override fun increaseViewCounter(guid: String) {
        mockProductsInList.first() {it.guid == guid}.viewCounter++
    }
}