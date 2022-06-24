package com.ruskaof.core_network_impl.data

import android.util.Log
import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import java.lang.RuntimeException
import javax.inject.Inject

class ProductApiImpl @Inject constructor(private val serviceApi: ServiceApi) : ProductApi {
    companion object {
        val productsListData: List<ProductInListDTO> = emptyList()
        val productsInfoData: List<ProductInfoDTO> = emptyList()
    }

    override fun getProductsList(): List<ProductInListDTO> {
        val response = serviceApi.getProductsList().execute()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw RuntimeException(response.errorBody().toString())
        }
    }

    override fun getProductsInfo(): List<ProductInfoDTO> {
        val response = serviceApi.getProductsInfo().execute()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw RuntimeException(response.errorBody().toString())
        }
    }

    override fun increaseViewCounter(guid: String) {
//        mockProductsInList.first() {it.guid == guid}.viewCounter++
    }
}