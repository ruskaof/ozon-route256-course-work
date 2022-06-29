package com.ruskaof.data_updater_impl.di

import com.ruskaof.core_network_api.ProductApi

interface DataUpdaterDependencies {
    fun getProductApi(): ProductApi
}