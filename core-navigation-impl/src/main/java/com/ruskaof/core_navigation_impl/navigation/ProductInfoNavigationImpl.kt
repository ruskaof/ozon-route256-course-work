package com.ruskaof.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.ruskaof.feature_pdp_api.ProductInfoNavigationApi
import com.ruskaof.feature_pdp_impl.presentation.view.ProductInfoFragment
import javax.inject.Inject

class ProductInfoNavigationImpl @Inject constructor(): ProductInfoNavigationApi {
    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != ProductInfoFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(ProductInfoFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}