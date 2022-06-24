package com.ruskaof.core_network_api

import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO

interface ProductApi {
    fun getProductsList(): List<ProductInListDTO>
    fun getProductInfo(guid: String): ProductInfoDTO
    fun increaseViewCounter(guid: String)
}