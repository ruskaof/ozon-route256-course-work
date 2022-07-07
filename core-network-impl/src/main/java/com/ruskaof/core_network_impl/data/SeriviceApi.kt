package com.ruskaof.core_network_impl.data

import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import io.reactivex.Single
import retrofit2.http.GET

interface ServiceApi {
    @GET("ee6876a1-8c02-45aa-bde4-b91817a8b210")
    fun getProductsList(): Single<List<ProductInListDTO>>

    @GET("d1b4763b-a5ea-471f-83bf-796da466e3d8")
    fun getProductsInfo(): Single<List<ProductInfoDTO>>
}

const val BASE_URL = "https://run.mocky.io/v3/"