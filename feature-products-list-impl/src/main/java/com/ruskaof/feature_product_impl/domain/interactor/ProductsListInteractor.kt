package com.ruskaof.feature_product_impl.domain.interactor

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.ruskaof.feature_product_impl.domain.mapper.toVO
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO
import javax.inject.Inject

interface ProductsListInteractor {
    fun getProductsList(context: Context): List<ProductInListVO>?
    fun updateData(context: Context, lifecycleOwner: LifecycleOwner, onDataUpdated: () -> Unit)
    fun increaseViewCounter(guid: String)
}

class ProductsListInteractorImpl @Inject constructor(private val repository: ProductsListRepository): ProductsListInteractor {
    override fun getProductsList(context: Context): List<ProductInListVO>? {
        return repository.getProductsList(context)?.map { it.toVO() }
    }

    override fun increaseViewCounter(guid: String) {
        repository.increaseViewCounter(guid)
    }

    override fun updateData(context: Context, lifecycleOwner: LifecycleOwner, onDataUpdated: () -> Unit) {
        repository.updateData(context, lifecycleOwner, onDataUpdated)
    }
}