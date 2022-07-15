package com.ruskaof.data_updater_impl

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.ruskaof.core_context_injector.ContextInjectorComponent
import com.ruskaof.core_context_needer_api.ContextNeeder
import com.ruskaof.core_network_api.models.ProductInListDTO
import com.ruskaof.core_network_api.models.ProductInfoDTO
import com.ruskaof.core_utils.Constants
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.data_updater_api.model.DataStoreListHolder
import com.ruskaof.data_updater_api.model.ProductInListSaveObject
import com.ruskaof.data_updater_api.model.ProductInfoSaveObject
import com.ruskaof.data_updater_api.model.toDTO
import com.ruskaof.data_updater_impl.workers.ProductsInfoWorker
import com.ruskaof.data_updater_impl.workers.ProductsListWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit


val Context.productInfoDataStore: DataStore<DataStoreListHolder<ProductInfoSaveObject>> by dataStore(
    Constants.PRODUCTS_INFO_KEY,
    ProductsInfoSerializer
)

val Context.productsListDataStore: DataStore<DataStoreListHolder<ProductInListSaveObject>> by dataStore(
    Constants.PRODUCTS_LIST_KEY,
    ProductsListSerializer
)

class DataUpdaterImpl : DataUpdaterApi, ContextNeeder {
    private val context: Context = ContextInjectorComponent.get().getContext()

    override fun updateProductsData(
    ) {
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

    }

    override fun getCurrentSavedProductsInfo(): Flow<List<ProductInfoDTO>> {
        return context.productInfoDataStore.data.map { listHolder -> listHolder.list.map { item -> item.toDTO() } }
    }

    override fun getCurrentSavedProductsList(): Flow<List<ProductInListDTO>> {
        Log.d("debuug", "getCurrentSavedProductsList: trying to get")
        return context.productsListDataStore.data.map { listHolder ->
            listHolder.list.map { item -> item.toDTO() }.onEach {
                Log.d("debuug", "getCurrentSavedProductsList: got update")
            }
        }
    }

}