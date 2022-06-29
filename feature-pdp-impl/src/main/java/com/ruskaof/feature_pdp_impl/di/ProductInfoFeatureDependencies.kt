package com.ruskaof.feature_pdp_impl.di

import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.feature_pdp_api.ProductInfoNavigationApi

interface ProductInfoFeatureDependencies {
    fun productsApi(): ProductApi
    fun pdpNavigation(): ProductInfoNavigationApi
    fun getDataUpdaterApi(): DataUpdaterApi
}