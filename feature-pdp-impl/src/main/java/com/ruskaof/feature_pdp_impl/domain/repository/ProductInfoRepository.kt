package com.ruskaof.feature_pdp_impl.domain.repository

import com.ruskaof.core_network_api.models.ProductInfoDTO


interface ProductInfoRepository {
    fun getProductInfo(guid: String): ProductInfoDTO
}