package com.ruskaof.feature_product_impl.data.repository_impl

import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(
    private val dataUpdaterApi: DataUpdaterApi,
) :
    ProductsListRepository {
    override fun getProductsList(): Flow<List<ProductInListDTO>?> {
        return dataUpdaterApi.getCurrentSavedProductsList()
    }

    override fun increaseViewCounter(guid: String) {
        //TODO("Not yet implemented")
    }

    override fun updateData() {
        dataUpdaterApi.updateProductsData()
    }


}

