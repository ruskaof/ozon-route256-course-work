package com.ruskaof.core_network_impl.data

import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import javax.inject.Inject

class ProductApiImpl @Inject constructor(private val serviceApi: ServiceApi) : ProductApi {


    override suspend fun getProductsList(): List<ProductInListDTO> {
        return serviceApi.getProductsList()
    }

    override suspend fun getProductsInfo(): List<ProductInfoDTO> {
        return serviceApi.getProductsInfo()
    }

    override suspend fun increaseViewCounter(guid: String) {

    }
}