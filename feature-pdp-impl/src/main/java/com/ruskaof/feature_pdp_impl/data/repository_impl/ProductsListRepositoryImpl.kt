package com.ruskaof.feature_pdp_impl.data.repository_impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.work.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import com.ruskaof.feature_pdp_impl.di.ProductInfoFeatureComponent
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductInfoRepositoryImpl @Inject constructor(private val productApi: ProductApi) :
    ProductInfoRepository {
    override fun getProductInfo(guid: String, context: Context): ProductInfoDTO? {
        val sharedPreferences = context.getSharedPreferences("application2", Context.MODE_PRIVATE)
        var jsonData = sharedPreferences.getString("data2", "")
        val gson: Gson = Gson()
        val type: Type = object : TypeToken<List<ProductInfoDTO>>() {}.type
        var data = gson.fromJson<List<ProductInfoDTO>>(jsonData, type)
        if (data == null) data = emptyList()
        return data.find { it.guid == guid }
    }

    override fun updateData(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        onDataUpdated: () -> Unit
    ) {
        val workManager = WorkManager.getInstance(context)
        val productsListRequest = OneTimeWorkRequest.Builder(ProductsListWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .addTag("productsListRequest")
            .build()
        val productsInfoRequest = OneTimeWorkRequest.Builder(ProductsInfoWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .addTag("productsInfoRequest")
            .build()



        workManager.beginWith(productsListRequest).then(productsInfoRequest).enqueue()

        observeOutputData(
            workManager,
            lifecycleOwner,
            context = context,
            onDataUpdated,
            "productsListRequest",
            "productsInfoRequest"
        )
    }


    private fun observeOutputData(
        workManager: WorkManager,
        lifecycleOwner: LifecycleOwner,
        context: Context,
        onDataUpdated: () -> Unit,
        vararg names: String
    ) {
        workManager.getWorkInfosLiveData(
            WorkQuery.Builder.fromTags(listOf(*names))
                .build()
        ).observe(lifecycleOwner) { workInfoList ->

            if (workInfoList.isEmpty()) return@observe
            val workInfo = workInfoList.last()
            println("observeOutputData: workInfo = $workInfo")
            if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                val data = workInfo.outputData
                val response = data.getString("data2") ?: ""
                val sharedPreferences =
                    context.getSharedPreferences("application2", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.remove("data2")
                editor.putString("data2", response)

                editor.commit()
                onDataUpdated()
            }
        }
    }
}


class ProductsListWorker @Inject constructor(
    context: Context,
    workerParameters: WorkerParameters,
) : Worker(context, workerParameters) {
    @Inject
    lateinit var productApi: ProductApi

    override fun doWork(): Result {
        ProductInfoFeatureComponent.productInfoFeatureComponent?.inject(this)
        val gson = Gson()

        return try {
            val data = productApi.getProductsList()
            Result.success(Data.Builder().putString("data", gson.toJson(data)).build())
        } catch (e: Exception) {
            Result.failure()
        }
    }
}


class ProductsInfoWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    @Inject
    lateinit var productApi: ProductApi

    override fun doWork(): Result {
        ProductInfoFeatureComponent.productInfoFeatureComponent?.inject(this)
        val gson = Gson()

        return try {
            val data = productApi.getProductsInfo()
            Result.success(Data.Builder().putString("data2", gson.toJson(data)).build())
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
