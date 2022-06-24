package com.ruskaof.feature_product_impl.data.repository_impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.work.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.feature_product_impl.di.ProductFeatureComponent
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productApi: ProductApi) :
    ProductsListRepository {
    override fun getProductsList(context: Context): List<ProductInListDTO>? {
        val sharedPreferences = context.getSharedPreferences("application", Context.MODE_PRIVATE)
        var jsonData = sharedPreferences.getString("data", "")
        Log.d("eee", "getProductsList: $jsonData")
        val gson: Gson = Gson()
        val type: Type = object : TypeToken<List<ProductInListDTO>>() {}.type
        var data: List<ProductInListDTO>? = gson.fromJson<List<ProductInListDTO>>(jsonData, type)
        return data
    }

    override fun increaseViewCounter(guid: String) {
        productApi.increaseViewCounter(guid)
    }

    override fun updateData(context: Context, lifecycleOwner: LifecycleOwner, onDataUpdated: () -> Unit) {



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
            onDataUpdated,
            context = context,
            "productsListRequest",
            "productsInfoRequest"
        )
    }

    private fun observeOutputData(
        workManager: WorkManager,
        lifecycleOwner: LifecycleOwner,
        onDataUpdated: () -> Unit,
        context: Context,
        vararg names: String
    ) {
        workManager.getWorkInfosLiveData(
            WorkQuery.Builder.fromTags(listOf(*names))
                .build()
        ).observe(lifecycleOwner) { workInfoList ->
            if (workInfoList.isEmpty()) return@observe


            val workInfo = workInfoList.first()
            println("observeOutputData: workInfo = $workInfo")
            if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                val data = workInfo.outputData
                val response = data.getString("data") ?: ""
                Log.d("aaa", "observeOutputData: $response")
                val sharedPreferences =
                    context.getSharedPreferences("application", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                editor.remove("data")
                editor.putString("data", response)
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
        ProductFeatureComponent.productFeatureComponent?.inject(this)
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
        ProductFeatureComponent.productFeatureComponent?.inject(this)
        val gson = Gson()

        return try {
            val data = productApi.getProductsInfo()
            Result.success(Data.Builder().putString("data2", gson.toJson(data)).build())
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
