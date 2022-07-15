package com.ruskaof.feature_pdp_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaof.feature_pdp_impl.domain.interactor.ProductInfoInteractor
import com.ruskaof.feature_pdp_impl.presentation.view_objects.ProductInfoVO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ProductInfoViewModel(
    interactor: ProductInfoInteractor,
    guid: String
) : ViewModel() {

    private val _productInfoLD: MutableLiveData<ProductInfoVO?> = MutableLiveData()
    val productInfoLD: LiveData<ProductInfoVO?> = _productInfoLD


    init {
        interactor.getProductInfo(guid).onEach {
            _productInfoLD.value = it
        }.launchIn(viewModelScope)
    }
}