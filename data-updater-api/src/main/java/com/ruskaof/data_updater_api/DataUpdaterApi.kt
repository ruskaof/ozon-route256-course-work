package com.ruskaof.data_updater_api

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

interface DataUpdaterApi {
    fun updateProductsData(context: Context, lifecycleOwner: LifecycleOwner): LiveData<UpdateStatus>
}