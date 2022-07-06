package com.ruskaof.feature_product_impl.data.repository_impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruskaof.core_context_injector.ContextInjectorComponent
import com.ruskaof.core_context_needer_api.ContextNeeder
import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_utils.Constants
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import java.lang.reflect.Type
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val dataUpdaterApi: DataUpdaterApi,
    private val gson: Gson,
) :
    ProductsListRepository, ContextNeeder {
    private val context = ContextInjectorComponent.get().getContext()

    override fun getProductsList(): Single<List<ProductInListDTO>?> {
        val sharedPreferences =
            context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)

        val rxPreferences: RxSharedPreferences = RxSharedPreferences.create(sharedPreferences)
        val type: Type = object : TypeToken<List<ProductInListDTO>>() {}.type
        val jsonData = rxPreferences.getString(Constants.PRODUCTS_LIST_KEY, "")

        return Single.fromObservable(jsonData.asObservable().map { gson.fromJson(it, type) })
    }

    override fun increaseViewCounter(guid: String) {
        productApi.increaseViewCounter(guid)
    }

    override fun updateData(
        lifecycleOwner: LifecycleOwner
    ): BehaviorSubject<UpdateStatus> {
        return dataUpdaterApi.updateProductsData(lifecycleOwner)
    }

}

