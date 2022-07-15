package com.ruskaof.data_updater_impl.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.data_updater_api.model.toSaveObject
import com.ruskaof.data_updater_impl.di.DataUpdaterComponent
import com.ruskaof.data_updater_impl.productsListDataStore
import javax.inject.Inject

class ProductsListWorker(
    private val context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {
    @Inject
    lateinit var productApi: ProductApi


    override suspend fun doWork(): Result {

        DataUpdaterComponent.get().inject(this)
        return try {
            val data = productApi.getProductsList()


            context.productsListDataStore.updateData { listHolder ->
                listHolder.copy(data.map { item -> item.toSaveObject() })

            }


            Result.success()
        } catch (e: Exception) {

            Result.failure()
        }
    }

}