package com.ruskaof.data_updater_api

import androidx.lifecycle.LifecycleOwner
import io.reactivex.subjects.BehaviorSubject

interface DataUpdaterApi {
    fun updateProductsData(lifecycleOwner: LifecycleOwner): BehaviorSubject<UpdateStatus>
}