package com.ruskaof.feature_pdp_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruskaof.feature_pdp_impl.domain.interactor.ProductInfoInteractor
import com.ruskaof.feature_pdp_impl.presentation.view_objects.ProductInfoVO


class ProductInfoViewModel(interactor: ProductInfoInteractor, guid: String) : ViewModel() {

    private val _productInfoLD: MutableLiveData<ProductInfoVO> = MutableLiveData()
    val productInfoLD: LiveData<ProductInfoVO> = _productInfoLD

    init {
        _productInfoLD.value = interactor.getProductInfo(guid)
    }

}