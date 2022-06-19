package com.ruskaof.core_navigation_api

import com.ruskaof.feature_pdp_api.ProductInfoNavigationApi
import com.ruskaof.feature_products_api.ProductNavigationApi

interface NavigationApi {
    fun getProductNavigation(): ProductNavigationApi
    fun getProductInfoNavigation(): ProductInfoNavigationApi
}