package com.ruskaof.data_updater_impl.workers

import android.content.Context
import android.util.Log
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

//    override fun doWork(): Result {
//        DataUpdaterComponent.get().inject(this)
//
//        return try {
//            val data = productApi.getProductsList()
//            Result.success(
//                Data.Builder().putString(Constants.PRODUCTS_LIST_KEY, gson.toJson(data)).build()
//            )
//        } catch (e: Exception) {
//            Result.failure()
//        }
//    }

    override fun createWork(): Single<Result> {
        DataUpdaterComponent.get().inject(this)

        Log.d("debuug", "createWork: hello")
        return try {

            val data = productApi.getProductsList()
            Log.d("debuug", "createWork: is ok")

            data.map {
                Result.success(
                    Data.Builder().putString(Constants.PRODUCTS_LIST_KEY, gson.toJson(it)).build()
                )
            }
        } catch (e: Exception) {
            Log.d("debuug", "createWork: not ok")

            Single.error(e)
        }
    }

}