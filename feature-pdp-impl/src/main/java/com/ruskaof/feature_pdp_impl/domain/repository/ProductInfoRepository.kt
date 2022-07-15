package com.ruskaof.feature_pdp_impl.domain.repository

import com.ruskaof.core_network_api.models.ProductInfoDTO
import kotlinx.coroutines.flow.Flow


interface ProductInfoRepository {
    fun getProductInfo(guid: String): Flow<ProductInfoDTO?>
    fun updateData()
}