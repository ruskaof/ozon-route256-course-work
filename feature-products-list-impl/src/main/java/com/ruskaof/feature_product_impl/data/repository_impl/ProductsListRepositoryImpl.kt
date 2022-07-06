package com.ruskaof.feature_product_impl.data.repository_impl

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruskaof.core_context_injector.ContextHolder
import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_utils.Constants
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import io.reactivex.subjects.BehaviorSubject
import java.lang.reflect.Type
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val dataUpdaterApi: DataUpdaterApi,
    private val gson: Gson,
) :
    ProductsListRepository {
    private val context: Context = ContextHolder.get()

    override fun getProductsList(): List<ProductInListDTO>? {
        val sharedPreferences =
            context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val jsonData = sharedPreferences.getString(Constants.PRODUCTS_LIST_KEY, "")
        val type: Type = object : TypeToken<List<ProductInListDTO>>() {}.type
        return gson.fromJson(jsonData, type)
    }

    override fun increaseViewCounter(guid: String) {
        productApi.increaseViewCounter(guid)
    }

    override fun updateData(
    ): BehaviorSubject<UpdateStatus> {
        return dataUpdaterApi.updateProductsData(context)
    }

}

