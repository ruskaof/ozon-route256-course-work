package com.ruskaof.feature_product_impl.domain.interactor

import com.ruskaof.feature_product_impl.domain.mapper.toVO
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO
import javax.inject.Inject

interface ProductsListInteractor {
    fun getProductsList(): List<ProductInListVO>
    fun increaseViewCounter(guid: String)
}

class ProductsListInteractorImpl @Inject constructor(private val repository: ProductsListRepository): ProductsListInteractor {
    override fun getProductsList(): List<ProductInListVO> {
        return repository.getProductsList().map { it.toVO() }
    }

    override fun increaseViewCounter(guid: String) {
        repository.increaseViewCounter(guid)
    }

}