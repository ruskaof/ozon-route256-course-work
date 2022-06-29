package com.ruskaof.feature_pdp_impl.domain.interactor

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.feature_pdp_impl.domain.mapper.toVO
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import com.ruskaof.feature_pdp_impl.presentation.view_objects.ProductInfoVO
import javax.inject.Inject


interface ProductInfoInteractor {
    fun getProductInfo(guid: String, context: Context): ProductInfoVO?
    fun updateData(context: Context, lifecycleOwner: LifecycleOwner): LiveData<UpdateStatus>
}

class ProductInfoInteractorImpl @Inject constructor(private val repository: ProductInfoRepository):
    ProductInfoInteractor {

    override fun getProductInfo(guid: String, context: Context): ProductInfoVO? {
        return repository.getProductInfo(guid, context)?.toVO()
    }

    override fun updateData(
        context: Context,
        lifecycleOwner: LifecycleOwner
    ): LiveData<UpdateStatus> {
        return repository.updateData(context, lifecycleOwner)
    }
}