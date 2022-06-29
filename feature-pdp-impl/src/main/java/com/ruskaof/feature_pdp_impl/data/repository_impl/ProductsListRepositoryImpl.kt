package com.ruskaof.feature_pdp_impl.data.repository_impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruskaof.core_network_api.models.ProductInfoDTO
import com.ruskaof.core_utils.Constants
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import java.lang.reflect.Type
import javax.inject.Inject

class ProductInfoRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val dataUpdaterApi: DataUpdaterApi
) :
    ProductInfoRepository {
    override fun getProductInfo(guid: String, context: Context): ProductInfoDTO? {
        val sharedPreferences =
            context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        var jsonData = sharedPreferences.getString(Constants.PRODUCTS_INFO_KEY, "")
        val type: Type = object : TypeToken<List<ProductInfoDTO>>() {}.type
        var data = gson.fromJson<List<ProductInfoDTO>>(jsonData, type)
        if (data == null) data = emptyList()
        return data.find { it.guid == guid }
    }

    override fun updateData(
        context: Context,
        lifecycleOwner: LifecycleOwner
    ): LiveData<UpdateStatus> {
        return dataUpdaterApi.updateProductsData(context, lifecycleOwner)
    }

}



