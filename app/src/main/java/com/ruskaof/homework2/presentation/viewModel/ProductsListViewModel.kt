package com.ruskaof.homework2.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruskaof.homework2.domain.interactors.ProductsInteractor
import com.ruskaof.homework2.presentation.viewObject.ProductInListVO

class ProductsListViewModel(private val productsInteractor: ProductsInteractor) : ViewModel() {
    private val _productsListLD: MutableLiveData<List<ProductInListVO>> = MutableLiveData()
    val productListLD: LiveData<List<ProductInListVO>> = _productsListLD

    init {
        _productsListLD.value = productsInteractor.getProducts()
    }

    fun increaseViewCounter(guid: String) {
        productsInteractor.increaseViewCounter(guid)
        _productsListLD.value = productsInteractor.getProducts()
    }
}