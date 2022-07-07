package com.ruskaof.core_network_impl.data

import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import io.reactivex.Single
import javax.inject.Inject

class ProductApiImpl @Inject constructor(private val serviceApi: ServiceApi) : ProductApi {


    override fun getProductsList(): Single<List<ProductInListDTO>> {
        val result = serviceApi.getProductsList()

        return result
    }

    override fun getProductsInfo(): Single<List<ProductInfoDTO>> {
        val result = serviceApi.getProductsInfo()

        return result
    }

    override fun increaseViewCounter(guid: String) {
//        mockProductsInList.first() {it.guid == guid}.viewCounter++
    }
}