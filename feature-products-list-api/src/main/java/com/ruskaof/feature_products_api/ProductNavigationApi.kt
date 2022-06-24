package com.ruskaof.feature_products_api

import androidx.fragment.app.Fragment

interface ProductNavigationApi {
    fun navigateToProductInfo(fragment: Fragment, guid: String)
    fun isFeatureClosed(fragment: Fragment): Boolean
}