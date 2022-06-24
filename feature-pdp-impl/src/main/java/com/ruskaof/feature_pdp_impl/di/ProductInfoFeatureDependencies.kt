package com.ruskaof.feature_pdp_impl.di

import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.feature_pdp_api.ProductInfoNavigationApi

interface ProductInfoFeatureDependencies {
    fun productsApi(): ProductApi
    fun pdpNavigation(): ProductInfoNavigationApi
}