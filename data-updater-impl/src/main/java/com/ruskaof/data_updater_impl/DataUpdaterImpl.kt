package com.ruskaof.data_updater_impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.ruskaof.core_utils.Constants
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.data_updater_impl.workers.ProductsInfoWorker
import com.ruskaof.data_updater_impl.workers.ProductsListWorker
import java.util.concurrent.TimeUnit

class DataUpdaterImpl : DataUpdaterApi {
    private val _statusLD: MutableLiveData<UpdateStatus> = MutableLiveData()

    override fun updateProductsData(
        context: Context,
        lifecycleOwner: LifecycleOwner
    ): LiveData<UpdateStatus> {
        val workManager = WorkManager.getInstance(context)

        val productsListRequest = OneTimeWorkRequest.Builder(ProductsListWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .addTag(Constants.PRODUCTS_LIST_WORKER_NAME)
            .build()
        val productsInfoRequest = OneTimeWorkRequest.Builder(ProductsInfoWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .addTag(Constants.PRODUCTS_INFO_WORKER_NAME)
            .build()

        workManager.beginWith(productsListRequest).then(productsInfoRequest).enqueue()

        observeAndSet(
            context,
            lifecycleOwner,
            workManager,
            Constants.PRODUCTS_LIST_WORKER_NAME,
            Constants.PRODUCTS_INFO_WORKER_NAME
        )

        return _statusLD
    }


    private fun observeAndSet(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        workManager: WorkManager,
        vararg workerNames: String
    ) {
        workManager.getWorkInfosLiveData(
            WorkQuery.Builder.fromTags(listOf(*workerNames))
                .build()
        ).observe(lifecycleOwner) { workInfoList ->
            if (workInfoList.isEmpty()) return@observe


            val productsListWorkInfo =
                workInfoList.first() { it.tags.contains(Constants.PRODUCTS_LIST_WORKER_NAME) }
            if (productsListWorkInfo.state == WorkInfo.State.SUCCEEDED) {
                val data = productsListWorkInfo.outputData
                val response = data.getString(Constants.PRODUCTS_LIST_KEY) ?: ""
                val sharedPreferences =
                    context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                editor.remove(Constants.PRODUCTS_LIST_KEY)
                editor.putString(Constants.PRODUCTS_LIST_KEY, response)
                editor.apply()

                _statusLD.value = UpdateStatus.PRODUCTS_LIST_UPDATED
            }

            val productsInfoWorkInfo =
                workInfoList.first() { it.tags.contains(Constants.PRODUCTS_INFO_WORKER_NAME) }
            if (productsInfoWorkInfo.state == WorkInfo.State.SUCCEEDED) {
                val data = productsInfoWorkInfo.outputData
                val response = data.getString(Constants.PRODUCTS_INFO_KEY) ?: ""
                val sharedPreferences =
                    context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                editor.remove(Constants.PRODUCTS_INFO_KEY)
                editor.putString(Constants.PRODUCTS_INFO_KEY, response)
                editor.apply()

                _statusLD.value = UpdateStatus.PRODUCTS_INFO_UPDATED
            }
        }
    }
}