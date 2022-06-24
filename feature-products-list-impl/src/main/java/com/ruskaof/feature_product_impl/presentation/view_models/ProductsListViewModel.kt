package com.ruskaof.feature_product_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruskaof.feature_product_impl.domain.interactor.ProductsListInteractor
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO


class ProductsListViewModel(private val productsInteractor: ProductsListInteractor) : ViewModel() {
    private val _productsListLD: MutableLiveData<List<ProductInListVO>> = MutableLiveData()
    val productListLD: LiveData<List<ProductInListVO>> = _productsListLD

    private var productsList: List<ProductInListVO> = emptyList()

    init {
        productsList = productsInteractor.getProductsList()
        _productsListLD.value = productsList
    }

    fun increaseViewCounter(guid: String) {
        productsInteractor.increaseViewCounter(guid)
        productsList.first() { it.guid == guid }.viewCounter++
        _productsListLD.value = productsList
    }
}