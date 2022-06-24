package com.ruskaof.feature_product_impl.domain.repository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ruskaof.core_network_api.models.ProductInListDTO


interface ProductsListRepository {
    fun getProductsList(context: Context): List<ProductInListDTO>?
    fun increaseViewCounter(guid: String)
    fun updateData(context: Context, lifecycleOwner: LifecycleOwner, onDataUpdated: () -> Unit);
}