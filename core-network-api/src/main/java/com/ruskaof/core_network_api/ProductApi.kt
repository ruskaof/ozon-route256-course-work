package com.ruskaof.core_network_api

import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import io.reactivex.Single

interface ProductApi {
    fun getProductsList(): Single<List<ProductInListDTO>>
    fun getProductsInfo(): Single<List<ProductInfoDTO>>
    fun increaseViewCounter(guid: String)
}