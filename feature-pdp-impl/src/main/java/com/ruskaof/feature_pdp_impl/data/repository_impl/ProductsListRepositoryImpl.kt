package com.ruskaof.feature_pdp_impl.data.repository_impl

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruskaof.core_context_injector.ContextInjectorComponent
import com.ruskaof.core_context_needer_api.ContextNeeder
import com.ruskaof.core_network_api.models.ProductInfoDTO
import com.ruskaof.core_utils.Constants
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import io.reactivex.subjects.BehaviorSubject
import java.lang.reflect.Type
import javax.inject.Inject

class ProductInfoRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val dataUpdaterApi: DataUpdaterApi
) : ProductInfoRepository, ContextNeeder {
    @Inject
    lateinit var context: Context

    init {
        ContextInjectorComponent.get().inject(this)
    }

    override fun getProductInfo(guid: String): ProductInfoDTO? {
        val sharedPreferences =
            context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val jsonData = sharedPreferences.getString(Constants.PRODUCTS_INFO_KEY, "")
        val type: Type = object : TypeToken<List<ProductInfoDTO>>() {}.type
        val data = gson.fromJson<List<ProductInfoDTO>>(jsonData, type)
        return data.find { it.guid == guid }
    }

    override fun updateData(
    ): BehaviorSubject<UpdateStatus> {
        return dataUpdaterApi.updateProductsData()
    }

}



