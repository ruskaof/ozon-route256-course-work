package com.ruskaof.feature_pdp_impl.data.repository_impl

import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInfoDTO
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import javax.inject.Inject

class ProductInfoRepositoryImpl @Inject constructor(private val productApi: ProductApi) :
    ProductInfoRepository {
    override fun getProductInfo(guid: String): ProductInfoDTO {
        return productApi.getProductInfo(guid)
    }

}