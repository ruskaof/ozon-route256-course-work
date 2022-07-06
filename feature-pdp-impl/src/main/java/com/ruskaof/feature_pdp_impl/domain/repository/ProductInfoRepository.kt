package com.ruskaof.feature_pdp_impl.domain.repository

import com.ruskaof.core_network_api.models.ProductInfoDTO
import com.ruskaof.data_updater_api.UpdateStatus
import io.reactivex.subjects.BehaviorSubject


interface ProductInfoRepository {
    fun getProductInfo(guid: String): ProductInfoDTO?
    fun updateData(): BehaviorSubject<UpdateStatus>
}