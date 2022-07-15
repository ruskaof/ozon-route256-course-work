package com.ruskaof.feature_product_impl.domain.interactor

import com.ruskaof.feature_product_impl.domain.mapper.toVO
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ProductsListInteractor {
    fun getProductsList(): Flow<List<ProductInListVO>?>
    fun updateData()
    fun increaseViewCounter(guid: String)
}

class ProductsListInteractorImpl @Inject constructor(private val repository: ProductsListRepository) :
    ProductsListInteractor {
    override fun getProductsList(): Flow<List<ProductInListVO>?> {
        return repository.getProductsList().map { list -> list?.map { item -> item.toVO() } }
    }

    override fun updateData() {
        return repository.updateData()
    }

    override fun increaseViewCounter(guid: String) {
        repository.increaseViewCounter(guid)
    }

}