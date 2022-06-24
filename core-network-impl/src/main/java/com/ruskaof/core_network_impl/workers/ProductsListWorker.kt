//package com.ruskaof.core_network_impl.workers
//
//import android.content.Context
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//import com.ruskaof.core_network_impl.data.ProductApiImpl
//
//class ProductsListWorker (
//    context: Context,
//    workerParameters: WorkerParameters
//) : Worker(context, workerParameters) {
//    override fun doWork(): Result {
//        val result = ServiceApiHolder.api.getProductsList().execute()
//        return if (result.isSuccessful) {
//            ProductApiImpl.productsListData = result.body()!!
//            Result.success()
//        } else {
//            Result.failure()
//        }
//    }
//
//}