package com.ruskaof.data_updater_api


import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import kotlinx.coroutines.flow.Flow

interface DataUpdaterApi {
    fun updateProductsData()

    fun getCurrentSavedProductsInfo(): Flow<List<ProductInfoDTO>>
    fun getCurrentSavedProductsList(): Flow<List<ProductInListDTO>>
}