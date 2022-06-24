package com.ruskaof.feature_pdp_impl.presentation.view_models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruskaof.feature_pdp_impl.domain.interactor.ProductInfoInteractor
import com.ruskaof.feature_pdp_impl.presentation.view_objects.ProductInfoVO


class ProductInfoViewModel(private val interactor: ProductInfoInteractor, private val guid: String) : ViewModel() {

    private val _productInfoLD: MutableLiveData<ProductInfoVO> = MutableLiveData()
    val productInfoLD: LiveData<ProductInfoVO> = _productInfoLD
    private var currentData: ProductInfoVO? = null


    fun updateData(context: Context, lifecycleOwner: LifecycleOwner, onDataUpdated: () -> Unit) {
        currentData = interactor.getProductInfo(guid, context)
        if (currentData == null) {
            interactor.updateData(context, lifecycleOwner, onDataUpdated)
        } else {
            onDataUpdated()
        }
        currentData = interactor.getProductInfo(guid, context)
        _productInfoLD.value = currentData
    }
}