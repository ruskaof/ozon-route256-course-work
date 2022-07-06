package com.ruskaof.data_updater_impl.workers

import android.content.Context
import androidx.work.Data
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_utils.Constants
import com.ruskaof.data_updater_impl.di.DataUpdaterComponent
import io.reactivex.Single
import javax.inject.Inject

class ProductsListWorker(
    context: Context,
    workerParameters: WorkerParameters,
) : RxWorker(context, workerParameters) {
    @Inject
    lateinit var productApi: ProductApi

    @Inject
    lateinit var gson: Gson

    override fun createWork(): Single<Result> {
        DataUpdaterComponent.get().inject(this)
        return try {
            val data = productApi.getProductsList()
            data.map {
                Result.success(
                    Data.Builder().putString(Constants.PRODUCTS_LIST_KEY, gson.toJson(it)).build()
                )
            }
        } catch (e: Exception) {
            Single.error(e)
        }
    }

}