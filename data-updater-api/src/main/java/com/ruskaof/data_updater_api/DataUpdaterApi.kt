package com.ruskaof.data_updater_api

import io.reactivex.subjects.BehaviorSubject

interface DataUpdaterApi {
    fun updateProductsData(): BehaviorSubject<UpdateStatus>
}