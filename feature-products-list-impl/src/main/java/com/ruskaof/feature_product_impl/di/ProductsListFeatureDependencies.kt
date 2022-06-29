package com.ruskaof.feature_product_impl.di

import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.feature_products_api.ProductNavigationApi

interface ProductsListFeatureDependencies {
    fun productsListApi(): ProductApi
    fun productNavigationApi(): ProductNavigationApi
    fun getDataUpdaterApi(): DataUpdaterApi
}