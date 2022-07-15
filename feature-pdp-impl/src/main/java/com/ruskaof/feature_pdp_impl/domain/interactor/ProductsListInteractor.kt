package com.ruskaof.feature_pdp_impl.domain.interactor

import com.ruskaof.feature_pdp_impl.domain.mapper.toVO
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import com.ruskaof.feature_pdp_impl.presentation.view_objects.ProductInfoVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface ProductInfoInteractor {
    fun getProductInfo(guid: String): Flow<ProductInfoVO?>
    fun updateData()
}

class ProductInfoInteractorImpl @Inject constructor(private val repository: ProductInfoRepository):
    ProductInfoInteractor {

    override fun getProductInfo(guid: String): Flow<ProductInfoVO?> {
        return repository.getProductInfo(guid).map { it?.toVO() }
    }

    override fun updateData() {
        repository.updateData()
    }
}