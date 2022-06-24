package com.ruskaof.feature_pdp_impl.domain.interactor

import com.ruskaof.feature_pdp_impl.domain.mapper.toVO
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import com.ruskaof.feature_pdp_impl.presentation.view_objects.ProductInfoVO
import javax.inject.Inject


interface ProductInfoInteractor {
    fun getProductInfo(guid: String): ProductInfoVO
}

class ProductInfoInteractorImpl @Inject constructor(private val repository: ProductInfoRepository):
    ProductInfoInteractor {

    override fun getProductInfo(guid: String): ProductInfoVO {
        return repository.getProductInfo(guid).toVO()
    }

}