package com.ruskaof.feature_product_impl.domain.repository

import androidx.lifecycle.LifecycleOwner
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.data_updater_api.UpdateStatus
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


interface ProductsListRepository {
    fun getProductsList(): Single<List<ProductInListDTO>?>
    fun increaseViewCounter(guid: String)
    fun updateData(lifecycleOwner: LifecycleOwner): BehaviorSubject<UpdateStatus>
}