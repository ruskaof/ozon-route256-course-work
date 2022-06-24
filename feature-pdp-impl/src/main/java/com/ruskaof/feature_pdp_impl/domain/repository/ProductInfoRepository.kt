package com.ruskaof.feature_pdp_impl.domain.repository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ruskaof.core_network_api.models.ProductInfoDTO


interface ProductInfoRepository {
    fun getProductInfo(guid: String, context: Context): ProductInfoDTO?
    fun updateData(context: Context, lifecycleOwner: LifecycleOwner, onDataUpdated: () -> Unit)
}