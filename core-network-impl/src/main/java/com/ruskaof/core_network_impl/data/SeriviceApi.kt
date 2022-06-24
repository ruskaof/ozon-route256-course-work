package com.ruskaof.core_network_impl.data

import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {
    @GET("50afcd4b-d81e-473e-827c-1b6cae1ea1b2")
    fun getProductsList(): Call<List<ProductInListDTO>>

    @GET("8c374376-e94e-4c5f-aa30-a9eddb0d7d0a")
    fun getProductsInfo(): Call<List<ProductInfoDTO>>
}

const val BASE_URL = "https://run.mocky.io/v3/"