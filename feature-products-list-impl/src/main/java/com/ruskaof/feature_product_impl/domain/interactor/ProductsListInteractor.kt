package com.ruskaof.feature_product_impl.domain.interactor

import androidx.lifecycle.LifecycleOwner
import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.feature_product_impl.domain.mapper.toVO
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

interface ProductsListInteractor {
    fun getProductsList(): Single<List<ProductInListVO>?>
    fun updateData(lifecycleOwner: LifecycleOwner): BehaviorSubject<UpdateStatus>
    fun increaseViewCounter(guid: String)
}

class ProductsListInteractorImpl @Inject constructor(private val repository: ProductsListRepository) :
    ProductsListInteractor {
    override fun getProductsList(): Single<List<ProductInListVO>?> {
        return repository.getProductsList().map { list -> list.map { dto -> dto.toVO() } }
    }

    override fun increaseViewCounter(guid: String) {
        repository.increaseViewCounter(guid)
    }

    override fun updateData(
        lifecycleOwner: LifecycleOwner
    ): BehaviorSubject<UpdateStatus> {
        return repository.updateData(lifecycleOwner)
    }
}