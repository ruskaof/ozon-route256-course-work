package com.ruskaof.feature_pdp_impl.domain.repository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.ruskaof.core_network_api.models.ProductInfoDTO
import com.ruskaof.data_updater_api.UpdateStatus


interface ProductInfoRepository {
    fun getProductInfo(guid: String, context: Context): ProductInfoDTO?
    fun updateData(context: Context, lifecycleOwner: LifecycleOwner): LiveData<UpdateStatus>
}