package com.ruskaof.feature_pdp_impl.data.repository_impl

import com.ruskaof.core_network_api.models.ProductInfoDTO
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ProductInfoRepositoryImpl @Inject constructor(
    private val dataUpdaterApi: DataUpdaterApi
) : ProductInfoRepository {

    override fun getProductInfo(guid: String): Flow<ProductInfoDTO?> {
        return dataUpdaterApi.getCurrentSavedProductsInfo()
            .map { list -> list.firstOrNull { item -> item.guid == guid } }
    }

    override fun updateData() {
        dataUpdaterApi.updateProductsData()
    }

}




