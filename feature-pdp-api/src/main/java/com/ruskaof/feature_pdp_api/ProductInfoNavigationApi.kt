package com.ruskaof.feature_pdp_api

import androidx.fragment.app.Fragment

interface ProductInfoNavigationApi {
    fun isFeatureClosed(fragment: Fragment): Boolean
}