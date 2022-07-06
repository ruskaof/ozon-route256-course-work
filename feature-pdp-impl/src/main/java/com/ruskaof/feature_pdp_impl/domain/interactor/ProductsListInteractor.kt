package com.ruskaof.feature_pdp_impl.domain.interactor

import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.feature_pdp_impl.domain.mapper.toVO
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import com.ruskaof.feature_pdp_impl.presentation.view_objects.ProductInfoVO
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


interface ProductInfoInteractor {
    fun getProductInfo(guid: String): ProductInfoVO?
    fun updateData(): BehaviorSubject<UpdateStatus>
}

class ProductInfoInteractorImpl @Inject constructor(private val repository: ProductInfoRepository):
    ProductInfoInteractor {

    override fun getProductInfo(guid: String): ProductInfoVO? {
        return repository.getProductInfo(guid)?.toVO()
    }

    override fun updateData(
    ): BehaviorSubject<UpdateStatus> {
        return repository.updateData()
    }
}