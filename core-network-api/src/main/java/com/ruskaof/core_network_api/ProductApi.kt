package com.ruskaof.core_network_api

import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO

interface ProductApi {
    suspend fun getProductsList(): List<ProductInListDTO>
    suspend fun getProductsInfo(): List<ProductInfoDTO>
    suspend fun increaseViewCounter(guid: String) // Gson
}