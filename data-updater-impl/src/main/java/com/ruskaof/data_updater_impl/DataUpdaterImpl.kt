package com.ruskaof.data_updater_impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.work.*
import com.ruskaof.core_context_injector.ContextInjectorComponent
import com.ruskaof.core_context_needer_api.ContextNeeder
import com.ruskaof.core_utils.Constants
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.data_updater_api.UpdateStatus
import com.ruskaof.data_updater_impl.workers.ProductsInfoWorker
import com.ruskaof.data_updater_impl.workers.ProductsListWorker
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class DataUpdaterImpl : DataUpdaterApi, ContextNeeder {
    private val _statusBS: BehaviorSubject<UpdateStatus> = BehaviorSubject.create()

    private val context: Context = ContextInjectorComponent.get().getContext()


    override fun updateProductsData(lifecycleOwner: LifecycleOwner): BehaviorSubject<UpdateStatus> {
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
            workManager,
            lifecycleOwner,
            Constants.PRODUCTS_LIST_WORKER_NAME,
            Constants.PRODUCTS_INFO_WORKER_NAME
        )

        return _statusBS
    }


    private fun observeAndSet(
        workManager: WorkManager,
        lifecycleOwner: LifecycleOwner,
        vararg workerNames: String
    ) {
        workManager.getWorkInfosLiveData(
            WorkQuery.Builder.fromTags(listOf(*workerNames))
                .build()
        ).observe(lifecycleOwner) { workInfoList ->
            if (workInfoList.isEmpty()) return@observe


            val productsListWorkInfo =
                workInfoList.find() { it.tags.contains(Constants.PRODUCTS_LIST_WORKER_NAME) }
            if (productsListWorkInfo?.state == WorkInfo.State.SUCCEEDED) {
                setDataToSharedPref(productsListWorkInfo, Constants.PRODUCTS_LIST_KEY)

                _statusBS.onNext(UpdateStatus.PRODUCTS_LIST_UPDATED)
            } else {
                _statusBS.onNext(UpdateStatus.ERROR)
            }

            val productsInfoWorkInfo =
                workInfoList.find() { it.tags.contains(Constants.PRODUCTS_INFO_WORKER_NAME) }
            if (productsInfoWorkInfo?.state == WorkInfo.State.SUCCEEDED) {
                setDataToSharedPref(productsInfoWorkInfo, Constants.PRODUCTS_INFO_KEY)
                _statusBS.onNext(UpdateStatus.PRODUCTS_INFO_UPDATED)
            } else {
                _statusBS.onNext(UpdateStatus.ERROR)
            }
        }
    }

    private fun setDataToSharedPref(workInfo: WorkInfo, key: String) {
        val data = workInfo.outputData
        val response = data.getString(key) ?: ""
        val sharedPreferences =
            context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.remove(key)
        editor.putString(key, response)
        editor.apply()
    }
}