package com.ruskaof.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.ruskaof.core_navigation_impl.R
import com.ruskaof.core_navigation_impl.di.FeatureInjectorProxy
import com.ruskaof.feature_product_impl.presentation.view.ProductsListFragment
import com.ruskaof.feature_products_api.ProductNavigationApi
import com.ruskaof.feature_pdp_impl.presentation.view.ProductInfoFragment
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor(): ProductNavigationApi {
    override fun navigateToProductInfo(fragment: Fragment, guid: String) {
        FeatureInjectorProxy.initFeaturePDPDI()
        val newFragment = ProductInfoFragment.Builder().setGuid(guid).build()
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainerView, newFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != ProductsListFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(ProductsListFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}