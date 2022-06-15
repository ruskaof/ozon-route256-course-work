package com.ruskaof.homework2.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruskaof.homework2.domain.interactors.ProductsInteractor
import com.ruskaof.homework2.presentation.viewObject.ProductVO

class ProductInfoViewModel(productsInteractor: ProductsInteractor, guid: String) : ViewModel() {

    private val _productInfoLD: MutableLiveData<ProductVO> = MutableLiveData()
    val productInfoLD: LiveData<ProductVO> = _productInfoLD

    init {
        _productInfoLD.value = productsInteractor.getProductById(guid)
    }
}