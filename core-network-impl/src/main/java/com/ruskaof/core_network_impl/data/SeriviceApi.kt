package com.ruskaof.core_network_impl.data

import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import retrofit2.http.GET

interface ServiceApi {
    // update6: 14c86254-e9b3-400f-a2e0-bb4f45ed0a43
    // update5 : b7fd4797-8bd8-41a5-bc64-ed6da11d569c
    // update4: 9d74aeae-4399-458f-926b-ec3c49966fb3
    // update3: 66e5d834-bd12-4bb7-8821-58855498b90b
    // updated2: 4dcdc54d-fea3-42ec-92bd-4b820a1e73a9
    // updated: 678b388f-77d9-416a-971f-6d128d603cf1
    // deprecated: ee6876a1-8c02-45aa-bde4-b91817a8b210
    @GET("14c86254-e9b3-400f-a2e0-bb4f45ed0a43")
    suspend fun getProductsList(): List<ProductInListDTO>

    @GET("d1b4763b-a5ea-471f-83bf-796da466e3d8")
    suspend fun getProductsInfo(): List<ProductInfoDTO>
}

const val BASE_URL = "https://run.mocky.io/v3/"
