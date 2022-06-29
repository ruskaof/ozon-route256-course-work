package com.ruskaof.feature_pdp_impl.presentation.view_models

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.feature_pdp_impl.domain.interactor.ProductInfoInteractor
import com.ruskaof.feature_pdp_impl.presentation.view_objects.ProductInfoVO


class ProductInfoViewModel(
    private val interactor: ProductInfoInteractor,
    private val guid: String
) : ViewModel() {

    private val _productInfoLD: MutableLiveData<ProductInfoVO> = MutableLiveData()
    val productInfoLD: LiveData<ProductInfoVO> = _productInfoLD


    fun updateData(context: Context, lifecycleOwner: LifecycleOwner) {
        interactor.updateData(context, lifecycleOwner).observe(lifecycleOwner) {
            if (it == UpdateStatus.PRODUCTS_INFO_UPDATED) {
                _productInfoLD.value = interactor.getProductInfo(guid, context)
            }
        }
    }
}